package com.chd.hao.manager.dao;

import com.chd.hao.manager.model.AdminModel;

import java.util.List;

public interface IAdminDAO {

    public List<AdminModel> selectAll();

    public List<AdminModel> selectAllWithPark();

    public AdminModel selectById(int id);

    public AdminModel selectByIdWithPark(int id);

    public AdminModel selectByName(String name);

    public AdminModel selectByNameWithPark(String name);

    public int addAdmin(AdminModel model);

    public int update(AdminModel model);

    public int count();

    public int deleteById(int id);

    public String selectPwd(String name);
}
