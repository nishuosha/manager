package com.chd.hao.manager.service.impl;

import com.chd.hao.manager.dao.IUserDAO;
import com.chd.hao.manager.model.UserModel;
import com.chd.hao.manager.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhanghao68 on 2018/4/17
 */

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource(name = "userDAO")
    private IUserDAO userDAO;

    @Override
    public int addUser(UserModel user) {
        return userDAO.insert(user);
    }

    @Override
    public List<UserModel> getAllUser() {
        return userDAO.selectAll();
    }

    @Override
    public UserModel getUserByName(String name) {
        return userDAO.selectByName(name);
    }

    @Override
    public int count() {
        return userDAO.count();
    }

    @Override
    public int deleteUserById(int id) {
        return userDAO.deleteById(id);
    }

    @Override
    public UserModel getUserById(int id) {
        return userDAO.selectById(id);
    }

    @Override
    public int update(UserModel user) {
        return userDAO.update(user);
    }

    @Override
    public String getPwd(String name) {
        return userDAO.selectPwd(name);
    }
}
