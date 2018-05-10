package com.chd.hao.manager.controller;

import com.chd.hao.manager.service.IReserveService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhanghao68 on 2018/5/10
 */
@Component
public class ScheduleTask {

    @Resource(name = "reserveService")
    private IReserveService reserveService;


    @Scheduled(cron = "0 0 2 * * ?")
    public void checkOutOfDate() {

        List<Integer> list = reserveService.getOutOfDateId();
        for(Integer i : list) {
            reserveService.updateStatus(i, "已过期");
        }
    }
}
