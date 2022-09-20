package config.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EurekaController {

    @Autowired
    private RegisterInter registerInter;

    /**
     * 获取所有的服务Id,即服务名  nacos的获取所有的服务id的接口返回的数据为json数据
     *
     * @return
     */
    @GetMapping("/apps")
    public List<String> getServices() {
        List<String> serviceIds = registerInter.getServices();
        return serviceIds;
    }

    /**
     * 根据serviceId 找到ServiceInstances
     *
     * @param serviceId
     * @return
     */
    @GetMapping("/instance")
    public List<ServiceInstance> getInstances(String serviceId) {
        List<ServiceInstance> serviceInstances = registerInter.getInstances(serviceId);
        return serviceInstances;
    }
}
