/*
package com.sleuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

*/
/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: AbstractTracedFilter
 * @projectName: erukeribbon
 * @description: 自定义过滤器
 * @date: 2022-08-11 16:28
 * https://juejin.cn/post/7011418596807016484
 * https://blog.51cto.com/u_11418075/4021192
 * https://cloud.tencent.com/developer/article/1927332
 **//*

@Component
public abstract class AbstractTracedFilter implements GlobalFilter, Ordered {

    @Autowired
    protected TracedPublisherFactory tracedPublisherFactory;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return tracedPublisherFactory.getTracedMono(traced(exchange, chain), exchange);
    }

    protected abstract Mono<Void> traced(ServerWebExchange exchange, GatewayFilterChain chain);

}
*/
