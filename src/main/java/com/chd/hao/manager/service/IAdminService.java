package com.chd.hao.manager.service;

import com.chd.hao.manager.model.AdminModel;
import com.chd.hao.manager.model.UserModel;

import java.util.List;

public interface IAdminService {

    public int addAdmin(AdminModel model);

    public List<AdminModel> getAllAdmin();

    public List<AdminModel> getAllAdminWithPark();

    public AdminModel getAdminByName(String name);

    public AdminModel getAdminByNameWithPark(String name);

    public int count();

    public int deleteById(int id);

    public AdminModel getById(int id);

    public AdminModel getByIdWithPark(int id);

    public int update(AdminModel model);

    public String getPwd(String name);
}
