package com.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author 微服务底座平台
 * @version 1.3.0
 * @title: SpringContextUtil
 * @projectName sungrid-console-coreops
 * @description: TODO
 * @date 2022/2/22 下午13:14
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    /**
     * 获取上下文对象
     */
    private static ApplicationContext applicationContext;
    /**
     * 打印日志
     */
    private Logger logger = LoggerFactory.getLogger(SpringContextUtil.class);

    /**
     * 获取 applicationContext
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
        logger.info("set applicationContext");
    }

    /**
     * 通过 name 获取 bean 对象
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过 class 获取 bean 对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过 name&#xff0c;clazz  获取指定的 bean 对象
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}
