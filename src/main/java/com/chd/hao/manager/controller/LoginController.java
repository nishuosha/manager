package com.chd.hao.manager.controller;

import com.chd.hao.manager.model.AdminModel;
import com.chd.hao.manager.model.UserModel;
import com.chd.hao.manager.service.IAdminService;
import com.chd.hao.manager.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhanghao68 on 2018/4/26
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Resource(name = "userService")
    private IUserService userService;

    @Resource(name = "adminService")
    private IAdminService adminService;

    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String userLogin(UserModel user, HttpServletRequest request) {
        UserModel model = userService.getUserByName(user.getUsername());

        if (model == null) {
            return "register";
        }
        if(!model.getUserpwd().equals(user.getUserpwd())) {
            return "error";
        }

        request.getSession().setAttribute("user", model);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String adminLogin(AdminModel admin, HttpServletRequest request) {
        AdminModel model = adminService.getAdminByNameWithPark(admin.getAdminName());

        if(model == null) {
            return "register";
        }

        if(!model.getAdminpwd().equals(admin.getAdminpwd())) {
            return "error";
        }
        request.getSession().setAttribute("user", model);
        return "success";
    }

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public String exit(HttpServletRequest request){
        //使session失效
        request.getSession().invalidate();
        return "redirect:/";
    }

}
