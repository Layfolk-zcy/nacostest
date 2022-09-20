package com.sleuth;

import brave.handler.MutableSpan;
import brave.handler.SpanHandler;
import brave.propagation.TraceContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.cloud.sleuth.autoconfig.instrument.messaging.TraceSpringIntegrationAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: SleuthConfig
 * @projectName: erukeribbon
 * @description: config
 * @date: 2022-09-02 14:51
 **/
@Configuration
@AutoConfigureBefore(TraceSpringIntegrationAutoConfiguration.class)
public class SleuthConfig {
    @Value("${spring.application.logyname:}")
    private String belLogy;
    @Value("${spring.application.appname:}")
    private String belApp;
    @Value("${spring.application.name:}")
    private String belSer;
    @Value("${server.port:}")
    private String port;
    @Value("${server.port:}")
    private String localEndPointPort;
    @Value("${eureka.instance.ip-address:}")
    private String localEndPointIp;

    @Bean
    SpanHandler handlerOne() {
        return new SpanHandler() {
            @Override
            public boolean end(TraceContext traceContext, MutableSpan span,
                               Cause cause) {
                String method = span.tag("mvc.controller.method");
                String path = span.tag("http.path");
                String targetPath = StringUtils.isNotBlank(path) ? path : method;

                span.tag("http.path", targetPath);
                span.tag("belSer", belSer);
                span.tag("belLogy", belLogy);
                span.tag("belApp", belApp);
                span.tag("serverName", belSer + port);
                span.tag("localEndPointIp", localEndPointIp);
                span.tag("localEndPointPort", localEndPointPort);
                return true;
            }
        };
    }
}
