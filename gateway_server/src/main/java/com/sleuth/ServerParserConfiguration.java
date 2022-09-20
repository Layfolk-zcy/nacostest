package com.sleuth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.http.HttpRequestParser;
import org.springframework.cloud.sleuth.http.HttpResponseParser;
import org.springframework.cloud.sleuth.instrument.web.HttpServerRequestParser;
import org.springframework.cloud.sleuth.instrument.web.HttpServerResponseParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: ServerParserConfiguration
 * @projectName: erukeribbon
 * @description: 服务端链路自定义
 * @date: 2022-08-11 15:44
 **/

@Configuration(proxyBeanMethods = false)
public class ServerParserConfiguration {

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

    @Bean(name = HttpServerRequestParser.NAME)
    HttpRequestParser myHttpRequestParser() {
        return (request, context, span) -> {
            // Span customization
            span.tag("belLogy", belLogy)
                    .tag("belApp", belApp)
                    .tag("belSer", belSer)
                    .tag("serverName", belSer + port)
                    .tag("localEndPointIp", localEndPointIp)
                    .tag("localEndPointPort", localEndPointPort);
            /* Object unwrap = request.unwrap();*/
            String method = request.method();
            String path = request.path();
            String route = request.route();
            String remoteIp = request.remoteIp();
            String url = request.url();
            span.tag("http.method", method);
            span.tag("http.path", path);
        };
    }

    @Bean(name = HttpServerResponseParser.NAME)
    HttpResponseParser myHttpResponseParser() {
        return (response, context, span) -> {
            // Span customization
            span.tag("belLogy", belLogy)
                    .tag("belApp", belApp)
                    .tag("belSer", belSer)
                    .tag("serverName", belSer + port)
                    .tag("localEndPointIp", localEndPointIp)
                    .tag("localEndPointPort", localEndPointPort);
            /* Object unwrap = response.unwrap();*/
            String method = response.method();
            String path = response.request().path();
            String route = response.route();
            String remoteIp = response.request().remoteIp();
            String url = response.request().url();
            span.tag("http.method", method);
            span.tag("http.path", path);
        };
    }
/*
响应客户端处理

@Bean
    Filter traceIdInResponseFilter(Tracer tracer) {
        return (request, response, chain) -> {
            Span currentSpan = tracer.currentSpan();
            if (currentSpan != null) {
                HttpServletResponse resp = (HttpServletResponse) response;
            }
            chain.doFilter(request, response);
        };
    }
*/
}
