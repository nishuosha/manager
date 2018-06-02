package com.chd.hao.manager.service.impl;

import com.chd.hao.manager.dao.IReserveDAO;
import com.chd.hao.manager.model.ParkModel;
import com.chd.hao.manager.model.ReserveModel;
import com.chd.hao.manager.service.IReserveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhanghao68 on 2018/5/2
 */
@Service("reserveService")
public class ReserveServiceImpl implements IReserveService {

    @Resource(name = "reserveDAO")
    private IReserveDAO reserveDAO;

    @Override
    public int add(ReserveModel model) {
        return reserveDAO.insert(model);
    }

    @Override
    public List<ReserveModel> getUserReserve(int uid) {
        return reserveDAO.selectUserReserve(uid);
    }

    @Override
    public List<ReserveModel> getAdminReserve(int aid) {
        return reserveDAO.selectAdminReserve(aid);
    }

    @Override
    public List<Integer> getNumByParkId(int pid, String reservetime) {
        return reserveDAO.selectNumByParkId(pid, reservetime);
    }

    @Override
    public Integer getByUserId(int uid, String reservetime) {
        return reserveDAO.selectByUserId(uid, reservetime);
    }

    @Override
    public Integer getByAdminId(int aid, String reservetime) {
        return reserveDAO.selectByAdminId(aid, reservetime);
    }

    @Override
    public int deleteById(int rid) {
        return reserveDAO.deleteById(rid);
    }

    @Override
    public int deleteByParkId(int pid) {
        return reserveDAO.deleteByParkId(pid);
    }

    @Override
    public int updateStatus(int rid, String status) {
        return reserveDAO.updateStatus(rid, status);
    }

    @Override
    public List<Integer> getOutOfDateId() {
        return reserveDAO.getOutOfDateId();
    }

    @Override
    public ReserveModel selectById(int rid) {
        return reserveDAO.selectById(rid);
    }

    @Override
    public List<Integer> getReservedId(int pid) {
        return reserveDAO.getReservedId(pid);
    }

    @Override
    public ReserveModel getReserveWithParkAndUser(int rid) {
        return reserveDAO.getReserveWithParkAndUser(rid);
    }

    @Override
    public ReserveModel getReserveWithParkAndAdmin(int rid) {
        return reserveDAO.getReserveWithParkAndAdmin(rid);
    }

    @Override
    public Integer getUserByRid(int rid) {
        return reserveDAO.getUserByRid(rid);
    }

    @Override
    public Integer getAdminByRid(int rid) {
        return reserveDAO.getAdminByRid(rid);
    }
}
