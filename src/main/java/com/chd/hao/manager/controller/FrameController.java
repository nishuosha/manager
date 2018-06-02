package com.chd.hao.manager.controller;

import com.chd.hao.manager.model.AdminModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhanghao68 on 2018/5/2
 */
@Controller
public class FrameController {


    //index
    @RequestMapping("/")
    String actIndex(HttpServletRequest request) {

        Object o = request.getSession().getAttribute("user");

        //跳转至未登录的首页
        if(o == null) {
            return "noindex";
        }

        if(o instanceof AdminModel) {
            //跳转至商家用户首页
            return "adminindex";
        } else {
            //跳转至个人用户首页
            return "index";
        }
    }

    @RequestMapping("/frame/guide")
    public String guide() {
        return "login";
    }


    @RequestMapping("/frame/adminside")
    public String actAdminSide() {
        return "adminside";
    }

    @RequestMapping("/frame/toggle")
    String actToggle() {
        return "toggle";
    }

    @RequestMapping("/frame/bottom")
    String actBottom() {
        return "bottom";
    }

    //side
    @RequestMapping("/frame/side")
    String actSide() {
        return "side";
    }

    //top
    @RequestMapping(value = "/frame/top")
    String actTop() {
        return "top";
    }

}
