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
                "预定时间：reservetime" + "\n" + "车位编号：parknum";


        try {
            MailUtil.sendMail(to, "预定信息变更", message.replace("name", pm.getName())
                    .replace("address", pm.getAddress()).replace("reservetime", rm.getReservetime())
                    .replace("parknum", String.valueOf(rm.getParknum())));
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("邮件发送失败!");
        }

        return reserveService.deleteById(id) == 1 ? "success" : "";
    }

    @ResponseBody
    @RequestMapping(value = "/getNumByParkId", method = RequestMethod.GET)
    public List<Integer> getNumByParkId(int pid, String time) {
        return getNumBypid(pid, time);
    }


    public List<Integer> getNumBypid(int pid, String time) {
        List<Integer> parklist = reserveService.getNumByParkId(pid, time);
        ParkModel p = parkService.getById(pid);
        int total = p.getCount();

        return getFree(total, parklist);
    }


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
            String ret = checkUserReserved(((UserModel) o).getId(), model.getReservetime());
            if("exist".equals(ret)) {
                return ret;
            }
            model.setUser((UserModel) o);
        } else {
            to = ((AdminModel) o).getEmail();
            String ret = checkAdminReserved(((AdminModel) o).getId(), model.getReservetime());
            if("exist".equals(ret)) {
                return ret;
            }
            model.setAdmin((AdminModel) o);
        }

        ParkModel p = parkService.getById(Integer.valueOf(pid));
        model.setPark(p);

        String enterName = getMinEnter(p.getCoordinate(), model.getParknum(), p.getLength());

        String message = "您选择的车位预定成功!" + "\n\n" + "车库名称：name" + "\n" + "车库地址：address" + "\n" +
                         "预定时间：reservetime" + "\n" + "车位编号：parknum" + "\n" + "最近入口：enterName";

        model.setCreatetime(DateUtil.format(new Date()));
        model.setStatus("已预定");

        try {
            MailUtil.sendMail(to, "预定信息", message.replace("name", p.getName())
                    .replace("address", p.getAddress()).replace("reservetime", model.getReservetime())
                    .replace("parknum", String.valueOf(model.getParknum())).replace("enterName", enterName));

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("邮件发送失败!");
        }
        return reserveService.add(model) == 1 ? "success" : "";
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
    public Map<String, Integer> getAdvice(int pid, String time) {

        List<Integer> freelist = getNumBypid(pid, time);
        if(freelist.size() == 0) {
            return null;
        }
        ParkModel p = parkService.getById(pid);

        return calculate(getFreeListCoor(freelist, p.getWidth(), p.getLength()), p.getLength(), p.getCoordinate());
    }

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

    public Integer getMinKey(Map<Integer, String> map) {
        Set<Integer> keySet = map.keySet();
        Integer[] keys = new Integer[keySet.size()];
        keySet.toArray(keys);
        Arrays.sort(keys);
        return keys[0];
    }

    public Integer getNumByCoor(String coor, int length) {
        int x = Integer.valueOf(coor.split(",")[0]);
        int y = Integer.valueOf(coor.split(",")[1]);
        return x * length + y;
    }

    private int getDistance(int i, int j, String coor) {
        int x = Integer.valueOf(coor.split(",")[0]);
        int y = Integer.valueOf(coor.split(",")[1]);
        return Math.abs(y - j ) + Math.abs(x - i);
    }


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
