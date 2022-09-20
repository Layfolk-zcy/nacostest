package com.factory;

import com.constant.NacosConstant;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
import org.springframework.util.StringUtils;

public class IRuleSimpleFactory {

    public IRuleSimpleFactory() {
    }

    public static IRule iRuleModStatic(String loadBalanceStrategy) {
        if (StringUtils.isEmpty(loadBalanceStrategy)) {
            return new RandomRule();
        }
        if (NacosConstant.ROUND_RULE.equals(loadBalanceStrategy)) {
            return new RoundRobinRule();
        }
        if (NacosConstant.RANDOM_RULE.equals(loadBalanceStrategy)) {
            return new RandomRule();
        }
        if (NacosConstant.WEIGHT_RULE.equals(loadBalanceStrategy)) {
            return new WeightedResponseTimeRule();
        }
        return new RandomRule();
    }

    public static String iRuleMod(String loadBalanceStrategy) {
        if (StringUtils.isEmpty(loadBalanceStrategy)) {
            return "com.netflix.loadbalancer.RandomRule";
        }
        if (NacosConstant.ROUND_RULE.equals(loadBalanceStrategy)) {
            return "com.netflix.loadbalancer.RoundRobinRule";
        }
        if (NacosConstant.RANDOM_RULE.equals(loadBalanceStrategy)) {
            return "com.netflix.loadbalancer.RandomRule";
        }
        if (NacosConstant.WEIGHT_RULE.equals(loadBalanceStrategy)) {
            /**nacos的权重负载策略*/
            //return "com.alibaba.cloud.nacos.ribbon.NacosRule";
            /**ribbon负载策略*/
            return "com.netflix.loadbalancer.WeightedResponseTimeRule";
        }
        return "com.netflix.loadbalancer.RandomRule";
    }
}
