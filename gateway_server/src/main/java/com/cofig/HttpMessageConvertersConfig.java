package com.cofig;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.stream.Collectors;

/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: HttpMessageConvertersConfig
 * @projectName: sungrid-basicwork
 * @description: feign的方法参数转换
 * @date: 2022-07-28 18:03
 **/
@Configuration
public class HttpMessageConvertersConfig {
    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }

}
