package com.chd.hao.manager.controller;

import com.chd.hao.manager.model.AdminModel;
import com.chd.hao.manager.model.ParkModel;
import com.chd.hao.manager.model.ReserveModel;
import com.chd.hao.manager.model.UserModel;
import com.chd.hao.manager.service.IParkService;
import com.chd.hao.manager.service.IReserveService;
import com.chd.hao.manager.util.DateUtil;
import com.chd.hao.manager.util.MailUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

/**
 * Created by zhanghao68 on 2018/4/26
 */
@Controller
@RequestMapping("/reserve")
public class ReserveController {

    @Resource(name = "reserveService")
    private IReserveService reserveService;

    @Resource(name = "parkService")
    private IParkService parkService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userReserve(HttpServletRequest request, Map<String, Object> map) {
        UserModel u = (UserModel) request.getSession().getAttribute("user");
        List<ReserveModel> urList = reserveService.getUserReserve(u.getId());
        map.put("urList", urList);
        return "userreserve";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminReserve(HttpServletRequest request, Map<String, Object> map) {
        AdminModel a = (AdminModel) request.getSession().getAttribute("user");
        List<ReserveModel> arList = reserveService.getAdminReserve(a.getId());
        map.put("arList", arList);
        return "adminreserve";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteById", method = RequestMethod.GET)
    public String deleteById(int id, HttpServletRequest request) {
        Object o = request.getSession().getAttribute("user");
        String to = "";

        if(o instanceof UserModel) {
            to = ((UserModel) o).getEmail();
        } else {
            to = ((AdminModel) o).getEmail();
        }

        ReserveModel rm = reserveService.selectById(id);

        ParkModel pm = rm.getPark();

        String message = "您取消了预定的车位!" + "\n\n" + "车库名称：name" + "\n" + "车库地址：address" + "\n" +
                "预定日期：reservetime" + "\n" + "预定时间：start:00 —— end:00" + "\n" + "车位编号：parknum";


        try {
            MailUtil.sendMail(to, "预定信息变更", message.replace("name", pm.getName())
                    .replace("address", pm.getAddress()).replace("reservetime", rm.getReservetime())
                    .replace("start", rm.getStart()).replace("end", rm.getEnd())
                    .replace("parknum", String.valueOf(rm.getParknum())));
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("邮件发送失败!");
        }

        return reserveService.deleteById(id) == 1 ? "success" : "";
    }

    @ResponseBody
    @RequestMapping(value = "/getNumByParkId", method = RequestMethod.GET)
    public List<Integer> getNumByParkId(int pid, String time, int start, int end) {
//        return getNumBypid(pid, time);
        return getNumByPidAndTime(pid, time, start, end);
    }

    //根据车库id，预定日期，预定时间获取空闲车位list
    public List<Integer> getNumByPidAndTime(int pid, String time, int start, int end) {
        //先根据车库id和日期获取空闲车位
        List<Integer> freeList = getNumBypid(pid, time);
        //根据车库id 分组获取已预订的车位的时间段
        List<ReserveModel> reservedGroup = reserveService.getGrouped(pid, time);
        for(ReserveModel model : reservedGroup) {
            //时间段数组
            String[] timeparts = model.getDatetime().split(";");
            int size = timeparts.length;
            int temp = 0;
            for(String s : timeparts) {
                int startInt = Integer.valueOf(s.split(",")[0]);
                int endInt = Integer.valueOf(s.split(",")[1]);

                if(startInt >= end || endInt <= start) {
                    temp ++;
                }
            }

            if(temp == size) {
                freeList.add(model.getParknum());
            }
        }
        Collections.sort(freeList);
        return freeList;
    }


    //根据车库id和预定时间获取空闲车位list
    public List<Integer> getNumBypid(int pid, String time) {
        List<Integer> parklist = reserveService.getNumByParkId(pid, time);
        ParkModel p = parkService.getById(pid);
        int total = p.getCount();

        return getFree(total, parklist);
    }

    //获取空闲车位编号list
    public List<Integer> getFree(int total, List<Integer> reserved) {
        List<Integer> totalList = getTotalList(total);
        if(reserved.size() == 0) {
            return totalList;
        }
        List<Integer> result = new ArrayList<>();
        for(int i = 0 ; i < total ; i++) {
            if(!reserved.contains(i)) {
                result.add(i);
            }
        }
        return result;
    }

    //获取所有车位编号list
    public List<Integer> getTotalList(int total) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0 ; i < total ; i++) {
            list.add(i);
        }
        return list;
    }


    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(ReserveModel model, HttpServletRequest request) {
        String t = request.getParameter("reservetime");
        model.setResevertime(t);
        String pid = request.getParameter("parkid");
        Object o = request.getSession().getAttribute("user");
        String to = "";
        if(o instanceof UserModel) {
            to = ((UserModel) o).getEmail();
//            String ret = checkUserReserved(((UserModel) o).getId(), model.getReservetime());
            String ret = "";
            if("exist".equals(ret)) {
                return ret;
            }
            model.setUser((UserModel) o);
        } else {
            to = ((AdminModel) o).getEmail();
//            String ret = checkAdminReserved(((AdminModel) o).getId(), model.getReservetime());
            String ret = "";
            if("exist".equals(ret)) {
                return ret;
            }
            model.setAdmin((AdminModel) o);
        }

        ParkModel p = parkService.getById(Integer.valueOf(pid));
        model.setPark(p);

        //发送邮件
        String enterName = getMinEnter(p.getCoordinate(), model.getParknum(), p.getLength());

        String message = "您选择的车位预定成功!" + "\n\n" + "车库名称：name" + "\n" + "车库地址：address" + "\n" +
                         "预定日期：reservetime" + "\n" + "预定时间：start:00 —— end:00" + "\n" +
                         "车位编号：parknum" + "\n" + "最近入口：enterName";

        model.setCreatetime(DateUtil.format(new Date()));
        model.setStatus("已预定");

        //添加数据
        reserveService.add(model);
        //发送邮件
        try {
            MailUtil.sendMail(to, "预定信息", message.replace("name", p.getName())
                    .replace("address", p.getAddress()).replace("reservetime", model.getReservetime())
                    .replace("start", model.getStart()).replace("end", model.getEnd())
                    .replace("parknum", String.valueOf(model.getParknum())).replace("enterName", enterName));

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("邮件发送失败!");
        }


        String m2 = "您预定的车位即将到期!" + "\n\n" + "车库名称：name" + "\n" + "车库地址：address" + "\n" +
                "预定日期：reservetime" + "\n" + "预定时间：start:00 —— end:00" + "\n" +
                "车位编号：parknum" + "\n\n" + "请您及时移动车辆，以免超时扣费!" ;

        Timer timer = new Timer();
        String finalTo = to;

        //提前15分钟邮件提醒
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    MailUtil.sendMail(finalTo, "预定信息提醒", m2.replace("name", p.getName())
                            .replace("address", p.getAddress()).replace("reservetime", model.getReservetime())
                            .replace("start", model.getStart()).replace("end", model.getEnd())
                            .replace("parknum", String.valueOf(model.getParknum())));
                } catch (MessagingException e) {
                    System.out.println("邮件发送失败!");
                    e.printStackTrace();
                }
            }
        };


        String m3 = "您预定的车位已到期!" + "\n\n" + "车库名称：name" + "\n" + "车库地址：address" + "\n" +
                "预定日期：reservetime" + "\n" + "预定时间：start:00 —— end:00" + "\n" +
                "车位编号：parknum" + "\n\n" + "谢谢您的使用，祝您生活愉快!" ;

        //预定时间到了提醒
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                try {
                    reserveService.updateStatus(model.getId(), "已过期");
                    MailUtil.sendMail(finalTo, "预定信息提醒", m3.replace("name", p.getName())
                            .replace("address", p.getAddress()).replace("reservetime", model.getReservetime())
                            .replace("start", model.getStart()).replace("end", model.getEnd())
                            .replace("parknum", String.valueOf(model.getParknum())));
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                timer.cancel();
            }
        };

        String end = String.valueOf(Integer.valueOf(model.getEnd()) - 1);
        String datetime1 = model.getReservetime() + " " + end + ":45:00";
        String datetime2 = model.getReservetime() + " " + model.getEnd() + ":00:00";
        try {
            timer.schedule(task, DateUtil.format3(datetime1));
            timer.schedule(task1, DateUtil.format3(datetime2));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "success";
    }

    private String getMinEnter(Map<String, String> coordinate, int parknum, int length) {

        int w = parknum / length;
        int l = parknum % length;

        Map<Integer, String> rmap = new HashMap<>();
        rmap.put(getDistance(w, l, coordinate.get("A")), "A");
        rmap.put(getDistance(w, l, coordinate.get("B")), "B");
        rmap.put(getDistance(w, l, coordinate.get("C")), "C");
        if (coordinate.get("D") != null) {
            rmap.put(getDistance(w, l, coordinate.get("D")), "D");
        }

        return rmap.get(getMinKey(rmap));

    }

    public String checkUserReserved(int id, String time) {

        Integer i = reserveService.getByUserId(id, time);
        if(i != null) {
            return "exist";
        }
        return "";
    }

    public String checkAdminReserved(int id, String time) {
        Integer i = reserveService.getByAdminId(id, time);
        if (i != null) {
            return "exist";
        }
        return "";
    }


    @ResponseBody
    @RequestMapping(value = "/getAdvice", method = RequestMethod.GET)
    public Map<String, Integer> getAdvice(int pid, String time, int start, int end) {

//        List<Integer> freelist = getNumBypid(pid, time);
        List<Integer> freelist = getNumByPidAndTime(pid, time, start, end);
        if(freelist.size() == 0) {
            return null;
        }
        ParkModel p = parkService.getById(pid);

        return calculate(getFreeListCoor(freelist, p.getWidth(), p.getLength()), p.getLength(), p.getCoordinate());
    }

    @ResponseBody
    @RequestMapping(value = "/remindFree", method = RequestMethod.GET)
    public String remindFree(int pid, String time, String start, String end, HttpServletRequest request) {

        Object o = request.getSession().getAttribute("user");
        String to = "";
        if(o instanceof AdminModel) {
            to = ((AdminModel) o).getEmail();
        } else {
            to = ((UserModel) o).getEmail();
        }

        ParkModel pm = parkService.getById(pid);
        int total = pm.getCount();


        Timer timer = new Timer();
        String finalTo = to;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
//                List<Integer> reserveList = reserveService.getNumByParkId(pid, time);
                List<Integer> freeList = getNumByPidAndTime(pid, time, Integer.valueOf(start), Integer.valueOf(end));
                if(freeList.size() != 0) {
                    System.out.println("有空位了");

                    try {
                        MailUtil.sendMail(finalTo, "车位提醒", "车位提醒!" + "\n\n" + "车库名称: " + pm.getName() + "\n"
                                        + "车库地址: " + pm.getAddress()  + "\n" + "日期: " + time
                                        + "\n" + "时间段：" + start + ":00 —— " + end + ":00"
                                        + "\n\n" + "消息: 已有空余车位,请及时预定!");
                    } catch (MessagingException e) {
                        e.printStackTrace();
                        System.out.println("邮件发送失败!");
                    }

                    timer.cancel();
                    return ;
                }

                Calendar c = Calendar.getInstance();
                if(c.get(Calendar.HOUR_OF_DAY) >= Integer.valueOf(end)) {
                    timer.cancel();
                }

            }
        };
        timer.scheduleAtFixedRate(task, 1000*10, 1000*10);
        return "";
    }

    //计算每个入口对应的最近的车位编号
    private Map<String, Integer> calculate(int[][] freeCoor, int length, Map<String, String> coordinate) {

        Map<String, Integer> rmap = new HashMap<>();
        Map<Integer, String> amap = new HashMap<>();
        Map<Integer, String> bmap = new HashMap<>();
        Map<Integer, String> cmap = new HashMap<>();
        Map<Integer, String> dmap = new HashMap<>();

        int w = freeCoor.length * freeCoor[0].length / length;
        for(int i = 0 ; i < w ; i++) {
            for(int j = 0 ; j < length ; j ++) {
                if(freeCoor[i][j] == 1) {

                    amap.put(getDistance(i, j, coordinate.get("A")), i + "," + j);
                    bmap.put(getDistance(i, j, coordinate.get("B")), i + "," + j);
                    cmap.put(getDistance(i, j, coordinate.get("C")), i + "," + j);
                    if(coordinate.get("D") != null ) {
                        dmap.put(getDistance(i, j, coordinate.get("D")), i + "," + j);
                    }
                }
            }
        }

        rmap.put("A", getNumByCoor(amap.get(getMinKey(amap)), length));
        rmap.put("B", getNumByCoor(bmap.get(getMinKey(bmap)), length));
        rmap.put("C", getNumByCoor(cmap.get(getMinKey(cmap)), length));
        if(dmap.size() != 0) {
            rmap.put("D", getNumByCoor(dmap.get(getMinKey(dmap)), length));
        }

        return rmap;
    }

    //获取最小的距离
    public Integer getMinKey(Map<Integer, String> map) {
        Set<Integer> keySet = map.keySet();
        Integer[] keys = new Integer[keySet.size()];
        keySet.toArray(keys);
        Arrays.sort(keys);
        return keys[0];
    }

    //根据坐标获取车位编号
    public Integer getNumByCoor(String coor, int length) {
        int x = Integer.valueOf(coor.split(",")[0]);
        int y = Integer.valueOf(coor.split(",")[1]);
        return x * length + y;
    }

    //获取两个坐标之间的距离
    private int getDistance(int i, int j, String coor) {
        int x = Integer.valueOf(coor.split(",")[0]);
        int y = Integer.valueOf(coor.split(",")[1]);
        return Math.abs(y - j ) + Math.abs(x - i);
    }

    //根据空闲车位list初始化二维数组每一项的值
    public int[][] getFreeListCoor(List<Integer> list, int width, int length) {
        int[][] coor = new int[width][length];
        for(int i = 0 ; i < list.size() ; i++) {
            int j = list.get(i);

            int l = j / length;
            int w = j % length;
            coor[l][w] = 1;
        }
        return coor;
    }
}
