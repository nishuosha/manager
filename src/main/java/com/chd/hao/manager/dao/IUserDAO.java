package com.chd.hao.manager.dao;


import com.chd.hao.manager.model.UserModel;

import java.util.List;

public interface IUserDAO {

    public List<UserModel> selectAll();

    public UserModel selectById(int id);

    public UserModel selectByName(String name);

    public int count();

    public int insert(UserModel model);

    public int deleteById(int id);

    public int update(UserModel model);

    public String selectPwd(String name);
}
