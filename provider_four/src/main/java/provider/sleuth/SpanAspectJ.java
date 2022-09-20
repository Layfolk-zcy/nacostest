package provider.sleuth;

/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: SpanAspectJ
 * @projectName: sungrid-basicwork
 * @description: 对所有含有controller  restcontroller进行aop
 * @date: 2022-07-14 10:22
 **/
/*@Aspect
@Component
public class SpanAspectJ {

    @Autowired
    private Tracer tracer;
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


    @Pointcut("@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Controller)")
    private void traceHandlerInterceptorPointcut() {
    }

    @Around("traceHandlerInterceptorPointcut()")
    public Object wrapWithCorrelationId(ProceedingJoinPoint pjp)
            throws Throwable {
        // 通过 DefaultTracer 添加额外的信息到tracer中
        this.tracer.currentSpan().tag("belLogy", belLogy);
        this.tracer.currentSpan().tag("belApp", belApp);
        this.tracer.currentSpan().tag("belSer", belSer);
        this.tracer.currentSpan().tag("serverName", belSer+port);
        this.tracer.currentSpan().tag("localEndPointIp", localEndPointIp);
        this.tracer.currentSpan().tag("localEndPointPort", localEndPointPort);
        return pjp.proceed();
    }
}*/
