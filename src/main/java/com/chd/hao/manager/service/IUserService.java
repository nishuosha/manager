package com.chd.hao.manager.service;

import com.chd.hao.manager.model.UserModel;

import java.util.List;

public interface IUserService {

    public int addUser(UserModel user);

    public List<UserModel> getAllUser();

    public UserModel getUserByName(String name);

    public int count();

    public int deleteUserById(int id);

    public UserModel getUserById(int id);

    public int update(UserModel user);

    public String getPwd(String name);
}
