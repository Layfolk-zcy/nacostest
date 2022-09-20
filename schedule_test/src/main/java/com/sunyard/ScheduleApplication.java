package com.sunyard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: ScheduleApplication
 * @projectName: erukeribbon
 * @description: 项目启动入口类
 * @date: 2022-06-30 17:51
 **/
@SpringBootApplication
@EnableScheduling
public class ScheduleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScheduleApplication.class, args);
    }
}
