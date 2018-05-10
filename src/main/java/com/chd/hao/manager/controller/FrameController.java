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

        if(o == null) {
            return "noindex";
        }

        if(o instanceof AdminModel) {
            return "adminindex";
        } else {
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
