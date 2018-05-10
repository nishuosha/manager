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

    public int updateStatus(int rid, String status);

    public List<Integer> getOutOfDateId();

    public ReserveModel selectById(int rid);
}
