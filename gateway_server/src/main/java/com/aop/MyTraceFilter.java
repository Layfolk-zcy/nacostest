package com.aop;

/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: MyTraceFilter
 * @projectName: erukeribbon
 * @description: trace拦截
 * @date: 2022-08-11 21:56
 **/
/*
public class MyTraceFilter implements WebFilter {

    @Autowired
    private Environment env;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return chain.filter(exchange).contextWrite(context -> {
            try {
                Span span = (Span) exchange.getAttributes().get(TraceWebFilter.class.getName() + ".TRACE");
                if (span != null) {
                    String traceId = span.context().traceId();
                    exchange.getResponse().getHeaders().add("Trace-Id", traceId);
                    span.tag("name", env.getProperty("spring.application.name"));
                }
            } catch (Exception e) {
            }
            return context;
        });
    }
}*/
