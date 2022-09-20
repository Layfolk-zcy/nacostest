package com.test;

import com.GatewayApplication;
import com.controller.FeignController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: Test
 * @projectName: erukeribbon
 * @description: test
 * @date: 2022-09-08 17:48
 **/
@SpringBootTest(classes = GatewayApplication.class)
@RunWith(SpringRunner.class)
public class Tests {

    @Autowired
    private FeignController feignController;

    @Test
    public void methodTest() {
        System.out.println(feignController.portTest());
    }
}
