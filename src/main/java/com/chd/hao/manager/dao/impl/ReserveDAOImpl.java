package com.chd.hao.manager.dao.impl;

import com.chd.hao.manager.dao.IReserveDAO;
import com.chd.hao.manager.model.ParkModel;
import com.chd.hao.manager.model.ReserveModel;
import com.chd.hao.manager.util.DateUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghao68 on 2018/5/2
 */
@Repository("reserveDAO")
public class ReserveDAOImpl implements IReserveDAO {

    @Resource(name = "sqlSessionTemplate")
    private SqlSession sqlSessionTemplate;

    @Override
    public int insert(ReserveModel model) {
        return sqlSessionTemplate.insert("reserve.insert", model);
    }

    @Override
    public List<ReserveModel> selectUserReserve(int uid) {
        return sqlSessionTemplate.selectList("reserve.selectUserReserve", uid);
    }

    @Override
    public List<ReserveModel> selectAdminReserve(int aid) {
        return sqlSessionTemplate.selectList("reserve.selectAdminReserve", aid);
    }

    @Override
    public List<Integer> selectNumByParkId(int pid, String reservetime) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", pid);
        map.put("reservetime", reservetime);
        return sqlSessionTemplate.selectList("reserve.selectNumByParkId", map);
    }

    @Override
    public List<ReserveModel> selectModelByParkId(int pid, String reservetime) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", pid);
        map.put("reservetime", reservetime);
        return sqlSessionTemplate.selectList("reserve.selectModelByParkId", map);
    }

    @Override
    public List<ReserveModel> selectGrouped(int pid, String reservetime) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", pid);
        map.put("reservetime", reservetime);
        return sqlSessionTemplate.selectList("reserve.selectGroup", map);
    }


    @Override
    public Integer selectByUserId(int uid, String reservetime) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", uid);
        map.put("reservetime", reservetime);
        return (Integer) sqlSessionTemplate.selectOne("reserve.selectByUserId", map);
    }

    @Override
    public Integer selectByAdminId(int aid, String reservetime) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", aid);
        map.put("reservetime", reservetime);
        return (Integer) sqlSessionTemplate.selectOne("reserve.selectByAdminId", map );
    }

    @Override
    public int deleteById(int rid) {
        return sqlSessionTemplate.delete("reserve.delete", rid);
    }

    @Override
    public int deleteByParkId(int pid) {
        return sqlSessionTemplate.delete("reserve.deleteByParkId", pid);
    }

    @Override
    public int updateStatus(int rid, String status) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", rid);
        map.put("status", status);
        return sqlSessionTemplate.update("reserve.updateStatus", map);
    }

    @Override
    public List<Integer> getOutOfDateId() {
        return sqlSessionTemplate.selectList("reserve.selectOutOfDate", DateUtil.format2(new Date()));
    }

    @Override
    public ReserveModel selectById(int rid) {
        return (ReserveModel) sqlSessionTemplate.selectOne("reserve.selectById", rid);
    }

    @Override
    public List<Integer> getReservedId(int pid) {
        return sqlSessionTemplate.selectList("reserve.selectReservedId", pid);
    }

    @Override
    public ReserveModel getReserveWithParkAndUser(int rid) {
        return (ReserveModel) sqlSessionTemplate.selectOne("reserve.selectReserveWithParkAndUser", rid);
    }

    @Override
    public ReserveModel getReserveWithParkAndAdmin(int rid) {
        return (ReserveModel) sqlSessionTemplate.selectOne("reserve.selectReserveWithParkAndAdmin", rid);
    }

    @Override
    public Integer getUserByRid(int rid) {
        return (Integer) sqlSessionTemplate.selectOne("reserve.selectUserByRid", rid);
    }

    @Override
    public Integer getAdminByRid(int rid) {
        return (Integer) sqlSessionTemplate.selectOne("reserve.selectAdminByRid", rid);
    }
}
