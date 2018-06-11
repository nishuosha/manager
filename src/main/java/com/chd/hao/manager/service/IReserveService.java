package com.chd.hao.manager.service;

import com.chd.hao.manager.model.ParkModel;
import com.chd.hao.manager.model.ReserveModel;

import java.util.List;

public interface IReserveService {

    public int add(ReserveModel model);

    public List<ReserveModel> getUserReserve(int uid);

    public List<ReserveModel> getAdminReserve(int aid);

    public List<Integer> getNumByParkId(int pid, String reservetime);

    public List<ReserveModel> getModelByParkId(int pid, String reservetime);

    public List<ReserveModel> getGrouped(int pid, String reservetime);

    public Integer getByUserId(int uid, String reservetime);

    public Integer getByAdminId(int aid, String reservetime);

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
