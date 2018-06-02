package com.chd.hao.manager.dao;

import com.chd.hao.manager.model.ParkModel;

import java.util.List;

public interface IParkDAO {

    public List<ParkModel> selectAll();

    public List<ParkModel> selectAllWithAdmin();

    public ParkModel selectById(int id);

    public ParkModel selectByIdWithAdmin(int id);

    public List<ParkModel> selectBySponsor(int id);

    public int count();

    public int insert(ParkModel model);

    public int update(ParkModel model);

    public int updateStatus(int status, int pid);

    public int delete(int id);

    public List<ParkModel> selectByCondition(ParkModel model);

    public int updateFree(int id, int free);

}
