package com.chd.hao.manager.service.impl;

import com.chd.hao.manager.dao.IAdminDAO;
import com.chd.hao.manager.model.AdminModel;
import com.chd.hao.manager.service.IAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhanghao68 on 2018/4/23
 */
@Service("adminService")
public class AdminServiceImpl implements IAdminService {

    @Resource(name = "adminDAO")
    private IAdminDAO adminDAO;

    @Override
    public int addAdmin(AdminModel model) {
        return adminDAO.addAdmin(model);
    }

    @Override
    public List<AdminModel> getAllAdmin() {
        return adminDAO.selectAll();
    }

    @Override
    public List<AdminModel> getAllAdminWithPark() {
        return adminDAO.selectAllWithPark();
    }

    @Override
    public AdminModel getAdminByName(String name) {
        return adminDAO.selectByName(name);
    }

    @Override
    public AdminModel getAdminByNameWithPark(String name) {
        return adminDAO.selectByNameWithPark(name);
    }

    @Override
    public int count() {
        return adminDAO.count();
    }

    @Override
    public int deleteById(int id) {
        return adminDAO.deleteById(id);
    }

    @Override
    public AdminModel getById(int id) {
        return adminDAO.selectById(id);
    }

    @Override
    public AdminModel getByIdWithPark(int id) {
        return adminDAO.selectByIdWithPark(id);
    }

    @Override
    public int update(AdminModel model) {
        return adminDAO.update(model);
    }

    @Override
    public String getPwd(String name) {
        return adminDAO.selectPwd(name);
    }
}
