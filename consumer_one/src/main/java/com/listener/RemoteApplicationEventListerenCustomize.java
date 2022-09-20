package com.listener;

import com.config.ApplicationContextProvider;
import com.factory.IRuleSimpleFactory;
import com.netflix.loadbalancer.IRule;
import com.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/***
 * 实现对客户端的负载均衡的动态修改
 * 1、使用一个监听SpringCloud bus的配置刷新事件接口对配置更新后的事件进行监听
 * 2、动态进行IRule对应的Bean的添加和删除，并且对NamedContextFactory工厂Map中的数据进行管理
 */

@Component
public class RemoteApplicationEventListerenCustomize implements ApplicationListener<RemoteApplicationEvent> {//, ImportBeanDefinitionRegistrar {

    @Autowired
    ConsumerService consumerService;
    @Autowired
    private SpringClientFactory springClientFactory;
    /**
     * 通过上下文获取yml中的配置，在该监听事件发生后springConfig获取到最新的配置后会重新刷新上下文
     */
    @Autowired
    private Environment environment;

    /**
     * 向容器中动态添加Bean
     *
     * @param ctx
     * @param beanName
     * @param beanClass
     */
    static void addBean(ConfigurableApplicationContext ctx, String beanName, Class beanClass) {
        BeanDefinitionRegistry beanDefReg = (DefaultListableBeanFactory) ctx.getBeanFactory();
        BeanDefinitionBuilder beanDefBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        BeanDefinition beanDef = beanDefBuilder.getBeanDefinition();
        if (!beanDefReg.containsBeanDefinition(beanName)) {
            beanDefReg.registerBeanDefinition(beanName, beanDef);
        }
    }

    /**
     * 从容器中移除Bean
     *
     * @param ctx
     * @param beanName
     */
    static void removeBean(ConfigurableApplicationContext ctx, String beanName) {
//        BeanDefinitionRegistry beanDefReg = (DefaultListableBeanFactory) ctx.getBeanFactory();
//        beanDefReg.getBeanDefinition(beanName);
//        beanDefReg.removeBeanDefinition(beanName);

        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) ctx.getBeanFactory();
        beanFactory.removeBeanDefinition(beanName);
    }

    /***
     * 监听事件  ，RemoteApplicationEvent的事件发布时会执行两次，spring  springmvc两个子父容器都会监听到
     * @param event
     */
    @Override
    public void onApplicationEvent(RemoteApplicationEvent event) {
//         Map<String, String> ribbonStrategyMap = RibbonGlobalMap.RIBBON_STRATEGY_MAP;
//        /**获取上下文配置*/
//         Integer ribbonFlag = environment
//                .getProperty("ribbon.flag", Integer.class);
//        /**ribbon配置标识为空时，不做任何处理，默认情况下eureka中此时的负载策略为轮询策略*/
//        if(ribbonStrategyMap.get("ribbonStrategy")==null || ribbonFlag == null || ribbonStrategyMap.get("ribbonStrategy").equals( ribbonFlag.toString())){
//            return;
//        }
//        /**不为空*/
//        NamedContextFactory namedContextFactory2 = (NamedContextFactory) ApplicationContextProvider.getBean("springClientFactory");
//        if (namedContextFactory2 != null) {
//            namedContextFactory2.destroy();
//        }
//        System.out.println("namedContextFactory工厂销毁方法已执行");

        IRule bean = ApplicationContextProvider.getBean(IRule.class);
        System.out.println("当前Ribbon使用的负载策略：" + bean.getClass());

        String beanNamesForType = ApplicationContextProvider.getApplicationContext().getBeanNamesForType(IRule.class)[0];
        /**销毁IRule*/
        removeBean((ConfigurableApplicationContext) ApplicationContextProvider.getApplicationContext(), beanNamesForType);
        /**注入IRule*/
        addBean((ConfigurableApplicationContext) ApplicationContextProvider.getApplicationContext(), beanNamesForType, IRuleSimpleFactory.iRuleModStatic(consumerService.getRibbonFlagValue()).getClass());
        IRule bean1 = ApplicationContextProvider.getBean(IRule.class);
        System.out.println("当前Ribbon使用的负载策略：" + bean1.getClass());

    }

    /***
     * 静态内部类动态注入指定类的类定义信息
     */
//    public static class MyImportDefinition implements ImportBeanDefinitionRegistrar{
//        @Override
//        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
//            String simpleName = ApplicationContextProvider.getApplicationContext().getBeanNamesForType(IRule.class)[0];
//            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
//            rootBeanDefinition.setBeanClass(IRule.class);
////            MutablePropertyValues propertyValues = new MutablePropertyValues();
////            rootBeanDefinition.setPropertyValues(propertyValues);
//            registry.registerBeanDefinition(simpleName, rootBeanDefinition);
//        }
//    }

}
