package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
/**开启服务降级 hytrix*/
//@EnableCircuitBreaker
public class ConsumerOneApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOneApplication.class, args);
    }
}
