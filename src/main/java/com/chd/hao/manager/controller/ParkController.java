package com.chd.hao.manager.controller;

import com.chd.hao.manager.model.AdminModel;
import com.chd.hao.manager.model.ParkModel;
import com.chd.hao.manager.service.IParkService;
import com.chd.hao.manager.util.DateUtil;
import org.apache.ibatis.executor.ReuseExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
}
