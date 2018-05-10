package com.chd.hao.manager.dao.impl;

import com.chd.hao.manager.dao.IParkDAO;
import com.chd.hao.manager.model.ParkModel;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghao68 on 2018/4/26
 */
@Repository("parkDAO")
public class ParkDAOImpl implements IParkDAO {

    @Resource(name = "sqlSessionTemplate")
    private SqlSession sqlSessionTemplate;

    @Override
    public List<ParkModel> selectAll() {
        return sqlSessionTemplate.selectList("park.selectAll");
    }

    @Override
    public List<ParkModel> selectAllWithAdmin() {
        return sqlSessionTemplate.selectList("park.selectAllWithAdmin");
    }

    @Override
    public ParkModel selectById(int id) {
        return (ParkModel) sqlSessionTemplate.selectOne("park.selectById", id);
    }

    @Override
    public ParkModel selectByIdWithAdmin(int id) {
        return (ParkModel) sqlSessionTemplate.selectOne("park.selectByIdWithAdmin", id);
    }

    @Override
    public List<ParkModel> selectBySponsor(int id) {
        return sqlSessionTemplate.selectList("park.selectBySponsor", id);
    }

    @Override
    public int count() {
        return (Integer) sqlSessionTemplate.selectOne("park.count");
    }

    @Override
    public int insert(ParkModel model) {
        return sqlSessionTemplate.insert("park.insert", model);
    }

    @Override
    public int update(ParkModel model) {
        return sqlSessionTemplate.update("park.update", model);
    }

    @Override
    public int delete(int id) {
        return sqlSessionTemplate.delete("park.delete", id);
    }

    @Override
    public List<ParkModel> selectByCondition(ParkModel model) {
        return sqlSessionTemplate.selectList("park.selectByCondition", model);
    }

    @Override
    public int updateFree(int id, int free) {
        Map<String, Integer> map = new HashMap<>();
        map.put("id", id);
        map.put("free", free);
        return sqlSessionTemplate.update("park.updateFree", map);
    }
}
