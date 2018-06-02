package com.chd.hao.manager.service.impl;

import com.chd.hao.manager.dao.IParkDAO;
import com.chd.hao.manager.model.ParkModel;
import com.chd.hao.manager.service.IParkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * Created by zhanghao68 on 2018/4/26
 */
@Service("parkService")
public class ParkServiceImpl implements IParkService {

    @Resource(name = "parkDAO")
    private IParkDAO parkDAO;

    @Override
    public List<ParkModel> getAll() {
        return parkDAO.selectAll();
    }

    @Override
    public List<ParkModel> getAllWithAdmin() {
        return parkDAO.selectAllWithAdmin();
    }

    @Override
    public ParkModel getById(int id) {
        return parkDAO.selectById(id);
    }

    @Override
    public ParkModel getByIdWithAdmin(int id) {
        return parkDAO.selectByIdWithAdmin(id);
    }

    @Override
    public List<ParkModel> getBySponsor(int id) {
        return parkDAO.selectBySponsor(id);
    }

    @Override
    public int getCount() {
        return parkDAO.count();
    }

    @Override
    public int update(ParkModel model) {
        return parkDAO.update(model);
    }

    @Override
    public int updateStatus(int status, int pid) {
        return parkDAO.updateStatus(status, pid);
    }

    @Override
    public int add(ParkModel model) {
        return parkDAO.insert(model);
    }

    @Override
    public int delete(int id) {
        return parkDAO.delete(id);
    }

    @Override
    public List<ParkModel> getByCondition(ParkModel model) {
        return parkDAO.selectByCondition(model);
    }
}
