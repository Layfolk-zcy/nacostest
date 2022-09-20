package eureka.service.sleuth;

/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: SleuthConfig
 * @projectName: erukeribbon
 * @description: config
 * @date: 2022-09-02 14:51
 **/
/*
@Configuration
@AutoConfigureBefore(TraceSpringIntegrationAutoConfiguration.class)
public class SleuthConfig {
    @Value("${spring.application.logyname:}")
    private String belLogy;
    @Value("${spring.application.appname:}")
    private String belApp;
    @Value("${spring.application.name:}")
    private String belSer;

    @Bean
    SpanHandler handlerOne() {
        return new SpanHandler() {
            @Override
            public boolean end(TraceContext traceContext, MutableSpan span,
                               Cause cause) {
                span.tag("belSer",belSer);
                span.tag("belLogy",belLogy);
                span.tag("belApp",belApp);
                span.tag("flag","flag");
                return true;
            }
        };
    }
}
*/
