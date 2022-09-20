package com.service.impl;

import com.global.RibbonGlobalMap;
import com.service.ConsumerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
/**消息总线的自动刷新,该注解只能放在service层@Service类上*/
@RefreshScope
public class ConsumerServiceImpl implements ConsumerService {

    @Value("${ribbon.flag:null}")
    private String ribbonFlag;

//    @Autowired
//    private ConsumerOneFeign  consumerOneFeign;

    /**
     * 使用hystrix注解 定义当前接口对应那种非法状态是进行服务熔断降级 @HystrixProperty中
     * 的name属性的值可以从HystrixCommandProperties类中查询对应的情形下的非法情况。fallbackMethod属性用于当请求的服务不可用是指定降级的方法
     */
//    @HystrixCommand(commandProperties = {@HystrixProperty(name = "",value = "")},fallbackMethod = "")
    @Override
    public String getRibbonFlagValue() {
        /**保持该map中只会存在一*/
        Map<String, String> ribbonGlobalMap = RibbonGlobalMap.RIBBON_STRATEGY_MAP;
        ribbonGlobalMap.put("ribbonStrategy", ribbonFlag);
        return ribbonFlag;
    }

//    @Override
//    public String feignMethod(){
//        return consumerOneFeign.decrease();
//    }
}
