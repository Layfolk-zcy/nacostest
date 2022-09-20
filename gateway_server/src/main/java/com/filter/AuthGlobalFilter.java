package com.filter;

import com.controller.FeignController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 认证过滤器
 *
 * @author 微服务底座平台
 * @version 1.3.0
 * @title: AuthGlobalFilter
 * @projectName Sungrid-Console
 * @description: TODO sungrid-console-gateway
 * @date 2022/2/22 下午13:14
 * 实现gateway的全局过滤器(拦截所有请求)，gateway中定义的过滤器会被组成过滤器链，并通过Ordered的值
 * 来决定filte的执行顺序。
 * Gateway Filter 根据路由配置匹配predicate的http请求才会做拦截处理
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    Logger log = LoggerFactory.getLogger(AuthGlobalFilter.class);

    @Autowired
    private FeignController feignController;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("GlobalFilter execute filter");
        /*String token = exchange.getRequest().getQueryParams().get("token").get(0);
        System.out.println(token);*/
        String upgrade = exchange.getRequest().getHeaders().getUpgrade();
        if ("websocket".equalsIgnoreCase(upgrade)) {
            return chain.filter(exchange);
        }
        /*String s = feignController.portTest();*/
        /*System.out.println(s);*/
        // 通行
        return chain.filter(exchange);


    }

    @Override
    public int getOrder() {
        return 0;
    }
}
