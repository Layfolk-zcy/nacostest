package provider.sleuth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.http.HttpRequestParser;
import org.springframework.cloud.sleuth.http.HttpResponseParser;
import org.springframework.cloud.sleuth.instrument.web.HttpClientRequestParser;
import org.springframework.cloud.sleuth.instrument.web.HttpClientResponseParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: ClientParserConfiguration
 * @projectName: sungrid-consoleops
 * @description: http client span customize
 * @date: 2022-08-10 10:11
 **/

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
            span.tag("belLogy", belLogy);
            span.tag("belApp", belApp);
            span.tag("belSer", belSer);
            span.tag("serverName", belSer + port);
            span.tag("localEndPointIp", localEndPointIp);
            span.tag("localEndPointPort", localEndPointPort);
            String method = request.method();
            String path = request.path();
            String route = request.route();
            String remoteIp = request.remoteIp();
            String url = request.url();
            span.tag("http.method", method);
            span.tag("http.path", path);
            /*span.tag("http.url", url);
            span.tag("http.remoteIp", remoteIp);
            span.tag("http.route", route);*/

           /* Object unwrap = request.unwrap();
            if (unwrap instanceof feign.Request) {
                feign.Request req = (feign.Request) unwrap;
                // Span customization
                span.tag("consumerClientRequestFeign", req.httpMethod().name());
            }*/
        };
    }

    // example for Feign
    @Bean(name = HttpClientResponseParser.NAME)
    HttpResponseParser myHttpClientResponseParser() {
        return (response, context, span) -> {
            // Span customization
            span.tag("belLogy", belLogy);
            span.tag("belApp", belApp);
            span.tag("belSer", belSer);
            span.tag("serverName", belSer + port);
            span.tag("localEndPointIp", localEndPointIp);
            span.tag("localEndPointPort", localEndPointPort);

            String method = response.method();
            String path = response.request().path();
            String route = response.route();
            String remoteIp = response.request().remoteIp();
            String url = response.request().url();
            span.tag("http.method", method);
            span.tag("http.path", path);
            /*span.tag("http.url", url);
            span.tag("http.remoteIp", remoteIp);
            span.tag("http.route", route);*/


           /* Object unwrap = response.unwrap();
            if (unwrap instanceof feign.Response) {
                feign.Response resp = (feign.Response) unwrap;
                // Span customization
                span.tag("consumerClientResponseFeign", String.valueOf(resp.status()));
            }*/
        };
    }
}
