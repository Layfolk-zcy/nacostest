package com.cofig;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author 微服务底座平台
 * @version 1.3.0
 * @title: LoadBalancedConfig
 * @projectName Sungrid-Console
 * @description: TODO sungrid-console-gateway
 * @date 2022/2/22 下午13:14
 * 开启负载策略
 */
@Configuration
public class LoadBalancedConfig {
    /*@Bean
    @LoadBalanced     // 如果不添加，无法通过服务名进行调用，只能通过ip调用
    public WebClient.Builder webBuilder(){
        return WebClient.builder();
    }*/

    @Bean
    @LoadBalanced     // 如果不添加，无法通过服务名进行调用，只能通过ip调用
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
