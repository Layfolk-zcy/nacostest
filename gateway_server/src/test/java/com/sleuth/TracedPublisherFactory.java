/*
package com.sleuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.CurrentTraceContext;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

*/
/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: TracedPublisherFactory
 * @projectName: erukeribbon
 * @description: 自定义工厂
 * @date: 2022-08-11 16:26
 **//*

@Component
public class TracedPublisherFactory {

    protected static final String TRACE_REQUEST_ATTR = Span.class.getName();

    @Value("${spring.application.logyname:}")
    private String belLogy;
    @Value("${spring.application.appname:}")
    private String belApp;
    @Value("${server.port:}")
    private String port;
    @Value("${spring.application.name:}")
    private String belSer;
    @Value("${server.port:}")
    private String localEndPointPort;
    @Value("${eureka.instance.ip-address:}")
    private String localEndPointIp;

    @Autowired(required = false)
    private Tracer tracer;
    @Autowired(required = false)
    private CurrentTraceContext currentTraceContext;

    public <T> Flux<T> getTracedFlux(Flux<T> publisher, ServerWebExchange exchange) {
        return new TracedFlux<>(publisher, tracer, currentTraceContext, (Span) exchange.getAttributes().get(TRACE_REQUEST_ATTR));
    }

    public <T> Mono<T> getTracedMono(Mono<T> publisher, ServerWebExchange exchange) {
        Span span = (Span) exchange.getAttributes().get(TRACE_REQUEST_ATTR);
        span.tag("gateway", "gateway")
                .tag("belLogy", belLogy)
                .tag("belApp", belApp)
                .tag("belSer", belSer)
                .tag("serverName", belSer + port)
                .tag("localEndPointIp", localEndPointIp)
                .tag("localEndPointPort", localEndPointPort);
        return new TracedMono<>(publisher, tracer, currentTraceContext, span);
    }
}
*/
