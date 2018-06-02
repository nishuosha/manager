package com.chd.hao.manager.controller;

import com.chd.hao.manager.model.AdminModel;
import com.chd.hao.manager.model.ParkModel;
import com.chd.hao.manager.model.ReserveModel;
import com.chd.hao.manager.service.IParkService;
import com.chd.hao.manager.service.IReserveService;
import com.chd.hao.manager.util.DateUtil;
import com.chd.hao.manager.util.MailUtil;
import org.apache.ibatis.executor.ReuseExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * Created by zhanghao68 on 2018/4/24
 */
@Controller
@RequestMapping("/park")
public class ParkController {

    @Resource(name = "parkService")
    private IParkService parkService;

    @Resource(name = "reserveService")
    private IReserveService reserveService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAll(Map<String, Object> map, boolean guide) {
        List<ParkModel> list = parkService.getAllWithAdmin();
        map.put("allpark", list);

        if(guide) {
            return "parkguide";
        }
        return "parkreserve";
    }

    @RequestMapping("/add")
    public String add(ParkModel model, HttpServletRequest request) {
        model.init();
        AdminModel user = (AdminModel) request.getSession().getAttribute("user");
        model.setSponsor(user);
        model.setTime(DateUtil.format(new Date()));
        model.setStatus(0);
        parkService.add(model);
        return "redirect:/park/admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getMyAll(HttpServletRequest request, Map<String, Object> map) {
        AdminModel user = (AdminModel) request.getSession().getAttribute("user");
        List<ParkModel> mylist = parkService.getBySponsor(user.getId());
        map.put("parks", mylist);
        return "admincreate";
    }

    //ajax
    @ResponseBody
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public ParkModel getById(int id) {
        return parkService.getByIdWithAdmin(id);
    }

    //ajax
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(ParkModel model) {
        int ret = parkService.update(model);
        if(ret == 1) {
            return "success";
        }
        return "";
    }

    //ajax
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(int id) {
        int ret = parkService.delete(id);
        if(ret == 1) {
            return "success";
        }
        return "";
    }

    //总数
    @ResponseBody
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int count() {
        return parkService.getCount();
    }

    //条件查询
    @RequestMapping(value = "/selectByCondition", method = RequestMethod.POST)
    public String getByCondition(ParkModel model, Map<String, Object> map, String entercount, boolean guide) {
        if(entercount.trim().length() != 0) {
            model.setEnterCount(Integer.valueOf(entercount));
        }
        map.put("model", model);
        List<ParkModel> codRet = parkService.getByCondition(model);
        map.put("allpark", codRet);
        if(guide) {
            return "parkguide";
        }
        return "parkreserve";
    }

    @RequestMapping(value = "/tocreate", method = RequestMethod.GET)
    public String toCreate() {
        return "createpark";
    }

    @RequestMapping("/reserve")
    public String reserve() {
        return "redirect:";
    }

//    停用车库
    @ResponseBody
    @RequestMapping(value = "/stopService", method = RequestMethod.GET)
    public String stopService(int pid) {
        //改变状态为1
        parkService.updateStatus(1, pid);

        // 此车库的所有预定信息
        List<Integer> reserved = reserveService.getReservedId(pid);
        if(reserved.size() != 0 ) {

            for(int i : reserved) {
                ReserveModel reserveModel = null;
                String to = "";
                if(reserveService.getUserByRid(i) != null) {
                    reserveModel = reserveService.getReserveWithParkAndUser(i);
                    to = reserveModel.getUser().getEmail();
                } else {
                    reserveModel = reserveService.getReserveWithParkAndAdmin(i);
                    to = reserveModel.getAdmin().getEmail();
                }


                String message = "您预定的车库已停用!" + "\n\n" + "车库名称：name" + "\n" +
                        "预定日期：reservetime" + "\n" + "预定时间：start -- end" + "\n" + "车位编号：parknum"
                        + "\n\n" + "对您带来的不便，我们深感抱歉。如有需要，请您预定其他车位!";

                try {
                    MailUtil.sendMail(to, "车库信息变更", message.replace("name", reserveModel.getPark().getName())
                            .replace("reservetime", reserveModel.getReservetime()).replace("start", reserveModel.getStart())
                            .replace("end", reserveModel.getEnd()).replace("parknum", String.valueOf(reserveModel.getParknum())));
                } catch (MessagingException e) {
                    System.out.println("邮件发送失败!");
                    e.printStackTrace();
                }

                reserveService.updateStatus(i, "已失效");
            }
        }

        return "success";
    }

    //启用车库
    @ResponseBody
    @RequestMapping(value = "/startService", method = RequestMethod.GET)
    public String startService(int pid) {

        parkService.updateStatus(0, pid);

        reserveService.deleteByParkId(pid);

        return "success";
    }
}
