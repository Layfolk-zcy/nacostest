package com.sunyard.exercise.schedule;

import com.sunyard.exercise.service.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: ScheduleClassTwo
 * @projectName: erukeribbon
 * @description: 定时任务类2
 * @date: 2022-06-30 17:53
 **/
@Component
public class ScheduleClassTwo {

    @Autowired
    private ServiceUtil serviceUtil;

    @Scheduled(cron = "30 * * * * *")
    public void taskTwo() {
        System.out.println("ScheduleClassOne#taskTwo::::" + serviceUtil.outSomething());
    }
}
