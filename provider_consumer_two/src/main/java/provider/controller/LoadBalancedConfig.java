package provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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

    @Autowired
    DiscoveryClient discoveryClient;

    /***
     * 开启负载策略，默认情况下使用轮询方式进行负载
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
