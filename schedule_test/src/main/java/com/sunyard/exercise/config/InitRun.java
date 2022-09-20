package com.sunyard.exercise.config;

import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: InitRun
 * @projectName: erukeribbon
 * @description: 初始化运行
 * @date: 2022-06-30 18:02
 **/
@Component
public class InitRun implements ApplicationRunner, ApplicationContextAware {

    /**
     * 获取Spring框架的上下文
     */
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        /*Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Component.class);
        for (Map.Entry<String, Object> entry : beansWithAnnotation.entrySet()) {

        }*/
        String className = "scheduleClassTwo";
        Class cls = applicationContext.getBean(className).getClass();
        Method m = cls.getDeclaredMethod("taskTwo");
        Object invoke = m.invoke(applicationContext.getBean(className));
    }


}
