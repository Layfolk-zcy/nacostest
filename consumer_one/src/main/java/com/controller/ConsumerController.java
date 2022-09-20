package com.controller;

import com.netflix.loadbalancer.IRule;
import com.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    public static final String URL = "http://provider/port";

    @Value("${server.port}")
    private String port;

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired(required = false)
    private SpringClientFactory springClientFactory;

    @GetMapping("/load")
    public String robinStrategy() {
        /**获取负载策略*/
        return "消费者端口：" + port + "," + "ribbon标识：" + consumerService.getRibbonFlagValue() + "," + "负载策略：";
    }

    @GetMapping("/value")
    public String getProviderPort() {
        if (springClientFactory == null) {
            return "负载策略配置失败";
        }
        IRule clientStrategy = springClientFactory.getInstance("consumer_one", IRule.class);
        ResponseEntity<String> result = restTemplate.getForEntity(ConsumerController.URL, String.class);
        /**result not null*/
        String portValue = result.getBody();
        return "服务提供者的服务端口号：" + portValue + "," + "客户端负载策略：" + clientStrategy + "," + "spring config负载标识：" + consumerService.getRibbonFlagValue();
    }

    /***
     * feign调用
     */
//    @PostMapping("")
//    public String feignMethod(){
//        return consumerService.feignMethod();
//    }
}
