package com.chd.hao.manager.dao;

import com.chd.hao.manager.model.ParkModel;
import com.chd.hao.manager.model.ReserveModel;

import java.util.List;

public interface IReserveDAO {

    public int insert(ReserveModel model);

    public List<ReserveModel> selectUserReserve(int uid);

    public List<ReserveModel> selectAdminReserve(int aid);

    public List<Integer> selectNumByParkId(int pid, String reservetime);

    public Integer selectByUserId(int uid, String reservetime);

    public Integer selectByAdminId(int aid, String reservetime);

    public int deleteById(int rid);

    public int deleteByParkId(int pid);

    public int updateStatus(int rid, String status);

    public List<Integer> getOutOfDateId();

    public ReserveModel selectById(int rid);

    public List<Integer> getReservedId(int pid);

    public ReserveModel getReserveWithParkAndUser(int rid);

    public ReserveModel getReserveWithParkAndAdmin(int rid);

    public Integer getUserByRid(int rid);

    public Integer getAdminByRid(int rid);
}
