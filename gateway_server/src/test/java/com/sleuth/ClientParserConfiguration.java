/*
package com.sleuth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.http.HttpRequestParser;
import org.springframework.cloud.sleuth.http.HttpResponseParser;
import org.springframework.cloud.sleuth.instrument.web.HttpClientRequestParser;
import org.springframework.cloud.sleuth.instrument.web.HttpClientResponseParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

*/
/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: ClientParserConfiguration
 * @projectName: sungrid-consoleops
 * @description: http client span customize
 * @date: 2022-08-10 10:11
 **//*

@Configuration(proxyBeanMethods = false)
public class ClientParserConfiguration {

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

    // example for Feign
    @Bean(name = HttpClientRequestParser.NAME)
    HttpRequestParser myHttpClientRequestParser() {
        return (request, context, span) -> {
            // Span customization
            span.name(request.method());
            span.tag("gateway-HttpClientRequestParser", "gateway-HttpClientRequestParser");
            span.tag("belLogy", belLogy);
            span.tag("belApp", belApp);
            span.tag("belSer", belSer);
            span.tag("serverName", belSer + port);
            span.tag("localEndPointIp", localEndPointIp);
            span.tag("localEndPointPort", localEndPointPort);
            Object unwrap = request.unwrap();
            if (unwrap instanceof feign.Request) {
                feign.Request req = (feign.Request) unwrap;
                // Span customization
                span.tag("gatewayClientRequestFeign", req.httpMethod().name());
            }
        };
    }

    // example for Feign
    @Bean(name = HttpClientResponseParser.NAME)
    HttpResponseParser myHttpClientResponseParser() {
        return (response, context, span) -> {
            // Span customization
            span.tag("gateway-HttpClientResponseParser", "gateway-HttpClientResponseParser");
            span.tag("belLogy", belLogy);
            span.tag("belApp", belApp);
            span.tag("belSer", belSer);
            span.tag("serverName", belSer + port);
            span.tag("localEndPointIp", localEndPointIp);
            span.tag("localEndPointPort", localEndPointPort);
            Object unwrap = response.unwrap();
            if (unwrap instanceof feign.Response) {
                feign.Response resp = (feign.Response) unwrap;
                // Span customization
                span.tag("gatewayClientResponseFeign", String.valueOf(resp.status()));
            }
        };
    }
}
*/
