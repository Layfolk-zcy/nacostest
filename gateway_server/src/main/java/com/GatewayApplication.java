package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: erukeribbon
 * @description: 网关服务启动类
 * @author:
 * @create: 2022-05-14 17:38
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
/*@EnableScheduling*/
//@EnableAspectJAutoProxy
@EnableFeignClients
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
