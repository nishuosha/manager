package com.chd.hao.manager.dao.impl;

import com.chd.hao.manager.dao.IUserDAO;
import com.chd.hao.manager.model.UserModel;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhanghao68 on 2018/4/17
 */

@Repository("userDAO")
public class UserDAOImpl implements IUserDAO {

    @Resource(name = "sqlSessionTemplate")
    private SqlSession sqlSessionTemplate;

    @Override
    public List<UserModel> selectAll() {
        return sqlSessionTemplate.selectList("user.selectAll");
    }

    @Override
    public UserModel selectById(int id) {
        return (UserModel) sqlSessionTemplate.selectOne("user.selectById", id);
    }

    @Override
    public UserModel selectByName(String name) {
        return (UserModel) sqlSessionTemplate.selectOne("user.selectByName", name);
    }

    @Override
    public int count() {
        return (Integer) sqlSessionTemplate.selectOne("user.count");
    }

    @Override
    public int insert(UserModel model) {
        return sqlSessionTemplate.insert("user.insert", model);
    }

    @Override
    public int deleteById(int id) {
        return sqlSessionTemplate.delete("user.deleteById", id);
    }

    @Override
    public int update(UserModel model) {
        return sqlSessionTemplate.update("user.update", model);
    }

    @Override
    public String selectPwd(String name) {
        return (String) sqlSessionTemplate.selectOne("user.selectPwd", name);
    }
}
