package com.chd.hao.manager.controller;

import com.chd.hao.manager.model.AdminModel;
import com.chd.hao.manager.model.UserModel;
import com.chd.hao.manager.service.IAdminService;
import com.chd.hao.manager.util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by zhanghao68 on 2018/4/23
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource(name = "adminService")
    private IAdminService adminService;

    //ajax
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addAdmin(AdminModel model) {
        model.setRegistertime(DateUtil.format(new Date()));
        int res = adminService.addAdmin(model);
        if(res == 1) {
            return "success";
        }
        return "failed";
    }

    @RequestMapping(value = "/toedit", method = RequestMethod.GET)
    public String toedit(int id, Map<String ,Object> map) {
        map.put("user", getAdminById(id));
        return "adminedit";
    }

    public AdminModel getAdminById(int id) {
        return adminService.getById(id);
    }

    //ajax
    @ResponseBody
    @RequestMapping(value = "/getByName", method = RequestMethod.GET)
    public String getByName(String name) {
        AdminModel model = adminService.getAdminByName(name);
        if(model == null) {
            return "success";
        }
        return "exist";
    }

    //ajax
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public String getById(int id, Map<String, Object> map) {
        map.put("user", getAdminById(id));
        return "admininfo";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(AdminModel model) {
        int res = adminService.update(model);
        if(res == 1) {
            return "redirect:/admin/getById?id=" + model.getId();
        }
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int count(){
        return adminService.count();
    }

    @RequestMapping("/getFromSession")
    public String getFromSession(HttpServletRequest request, Map<String, Object> map){
        AdminModel a = (AdminModel) request.getSession().getAttribute("user");
        map.put("user", a);
        return "admininfo";
    }

}
