package com.sunyard.exercise.schedule;

import com.sunyard.exercise.service.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: ScheduleClassOne
 * @projectName: erukeribbon
 * @description: 定时任务类1
 * @date: 2022-06-30 17:53
 **/
//@Component
public class ScheduleClassOne {

    @Autowired
    private ServiceUtil serviceUtil;

    @Scheduled(fixedDelay = 60000)
    public void taskOne() {
        System.out.println("ScheduleClassOne#taskOne::::" + serviceUtil.outSomething());
    }
}
