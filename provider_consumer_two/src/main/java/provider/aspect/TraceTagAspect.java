package provider.aspect;

/**
 * @program: erukeribbon
 * @description: 自定义Trace的tag字段的信息apo
 * @author:
 * @create: 2022-05-27 15:56
 **/

/*
@Aspect
@Component
public class TraceTagAspect {

    @Autowired
    private Tracer tracer;

    @Value("${spring.application.name}")
    private String applicationName;

//    全局过滤器中使用applicationContext工具类获取到Tracer设置tag的数据信息

//    aop实现自定义tag 在请求执行controller方法时使用apo将自定义tag信息添加到span中
//    1、服务在部署在指定生态的指定服务下时，在配置中心添加对应的生态名称 服务名称
//    2、获取在配置中心的生态名称，应用名称，在apo中的前置通知中将该数据设置到span的tag中
//    3、TODO 在es中查看数据状态，使用es的Java方式获取到数据对数据进行整合分析
//    tracer.currentSpan().tag("transId", "11111");

    // Service层切点
    @Pointcut("@annotation(provider.common.annotation.AopAnnotation)")
    public void servicePointcut() {
    }

    // 切点方法执行前运行
    @Before(value = "servicePointcut()")
    public void doBefore(JoinPoint joinPoint) {
        tracer.currentSpan().tag("provider_consumer_two", "aop");
        System.out.println("Start time: " + System.currentTimeMillis());
    }
}
*/
