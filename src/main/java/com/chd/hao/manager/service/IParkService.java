package com.chd.hao.manager.service;

import com.chd.hao.manager.model.ParkModel;

import java.util.List;

public interface IParkService {

    public List<ParkModel> getAll();

    public List<ParkModel> getAllWithAdmin();

    public ParkModel getById(int id);

    public ParkModel getByIdWithAdmin(int id);

    public List<ParkModel> getBySponsor(int id);

    public int getCount();

    public int update(ParkModel model);

    public int updateStatus(int status, int pid);

    public int add(ParkModel model);

    public int delete(int id);

    public List<ParkModel> getByCondition(ParkModel model);

}
