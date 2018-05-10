package com.chd.hao.manager.dao.impl;

import com.chd.hao.manager.dao.IAdminDAO;
import com.chd.hao.manager.model.AdminModel;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhanghao68 on 2018/4/23
 */
@Repository("adminDAO")
public class AdminDAOImpl implements IAdminDAO {

    @Resource(name = "sqlSessionTemplate")
    private SqlSession sqlSessionTemplate;

    @Override
    public List<AdminModel> selectAll() {
        return sqlSessionTemplate.selectList("admin.selectAll");
    }

    @Override
    public List<AdminModel> selectAllWithPark() {
        return sqlSessionTemplate.selectList("admin.selectAllWithPark");
    }

    @Override
    public AdminModel selectById(int id) {
        return (AdminModel) sqlSessionTemplate.selectOne("admin.selectById", id);
    }

    @Override
    public AdminModel selectByIdWithPark(int id) {
        return (AdminModel) sqlSessionTemplate.selectOne("admin.selectByIdWithPark", id);
    }

    @Override
    public AdminModel selectByName(String name) {
        return (AdminModel) sqlSessionTemplate.selectOne("admin.selectByName", name);
    }

    @Override
    public AdminModel selectByNameWithPark(String name) {
        return (AdminModel) sqlSessionTemplate.selectOne("admin.selectByNameWithPark", name);
    }

    @Override
    public int addAdmin(AdminModel model) {
        return sqlSessionTemplate.insert("admin.insert", model);
    }

    @Override
    public int update(AdminModel model) {
        return sqlSessionTemplate.update("admin.update", model);
    }

    @Override
    public int count() {
        return (int) sqlSessionTemplate.selectOne("admin.count");
    }

    @Override
    public int deleteById(int id) {
        return sqlSessionTemplate.delete("admin.deleteById", id);
    }

    @Override
    public String selectPwd(String name) {
        return (String) sqlSessionTemplate.selectOne("admin.selectPwd", name);
    }
}
