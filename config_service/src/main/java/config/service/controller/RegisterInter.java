package config.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 微服务底座平台
 * @version 1.3.0
 * @title: RegisterInter
 * @projectName Sungrid-Console
 * @description: TODO sungrid-console-register
 * @date 2022/2/22 下午13:14
 * <p>
 * https://blog.csdn.net/HHYJX/article/details/107302641 Eureka对外暴露的api接口
 */
@Component
public class RegisterInter {
    Logger logger = LoggerFactory.getLogger(RegisterInter.class);

    // 服务发现客户端,不同的服务发现组件都实现了该客户端接口，对其服务发现接口进行了封装
    // 获取对用服务注册器上的服务 服务实例等接口信息
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 获取所有的服务Id,即服务名  nacos的获取所有的服务id的接口返回的数据为json数据
     *
     * @return
     */
    public List<String> getServices() {
        List<String> serviceIds = discoveryClient.getServices();
        return serviceIds;
    }

    /**
     * 根据serviceId 找到ServiceInstances
     *
     * @param serviceId
     * @return
     */
    public List<ServiceInstance> getInstances(String serviceId) {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceId);
        return serviceInstances;
    }

}
