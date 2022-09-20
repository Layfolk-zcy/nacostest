package com.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/***
 *
 */
@Configuration
//@RibbonClients(defaultConfiguration = RestTemplateConfig.class)
public class RestTemplateConfig {

    // 默认我使用了随机策略，方便测试
    public static String ruleClassName = "com.netflix.loadbalancer.RandomRule";

    /**
     * 用于对客户端或者负载均衡的配置，它的默认实现类为DefaultClientConfigImpl
     */
    @Autowired(required = false)
    private IClientConfig config;

    @Autowired
    private ConsumerService consumerService;

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    @Primary
//    public IRule ribbonRule() {
//
//        /***
//         * 配置中心标识符
//         */
//        String strategy = IRuleSimpleFactory.iRuleMod(consumerService.getRibbonFlagValue());
//        if (!StringUtils.isEmpty(strategy)) {
//            ruleClassName = strategy;
//        }
//        AbstractLoadBalancerRule rule = null;
//        try {
//            rule = (AbstractLoadBalancerRule) Class.forName(ruleClassName).newInstance();
//            rule.initWithNiwsConfig(config);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return rule;
//    }

    @Bean
    public IRule ribbonRule() {
        return new RoundRobinRule();
    }
}
