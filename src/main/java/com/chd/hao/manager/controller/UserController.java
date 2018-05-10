package com.chd.hao.manager.controller;

import com.chd.hao.manager.model.UserModel;
import com.chd.hao.manager.service.IUserService;
import com.chd.hao.manager.util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * Created by zhanghao68 on 2018/4/23
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userService")
    private IUserService userService;

    //ajax
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(UserModel model, Map<String, Object> map) {

        model.setRegistertime(DateUtil.format(new Date()));
        map.put("user", model);
        int res = userService.addUser(model);
        if(res == 1) {
            return "success";
        }
        return "failed";
    }

    //ajax
    @ResponseBody
    @RequestMapping(value = "/getByName", method = RequestMethod.GET)
    public String getByName(String name) {
        UserModel model = userService.getUserByName(name);
        if(model == null) {
            return "success";
        }
        return "exist";
    }

    //修改完之后查看
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public String getById(int id, Map<String, Object> map) {
        UserModel u = getUserById(id);
        map.put("user", u);
        return "userinfo";
    }

    public UserModel getUserById(int id){
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/toedit", method = RequestMethod.GET)
    public String toedit(int id, Map<String, Object> map) {
        UserModel u = getUserById(id);
        map.put("user", u);
        return "useredit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(UserModel model) {
        int res = userService.update(model);
        if(res == 1) {
            return "redirect:/user/getById?id=" + model.getId();
        }
        return "";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String allUser(Map<String, Object> map) {
        List<UserModel> list = userService.getAllUser();
        map.put("userList", list);
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int count() {
        return userService.count();
    }

    @RequestMapping(value = "/getBySession")
    public String getFromSession(HttpServletRequest request, Map<String, Object> map) {
        UserModel u = (UserModel) request.getSession().getAttribute("user");
        UserModel um = getUserById(u.getId());
        map.put("user", um);
        return "userinfo";
    }
}
