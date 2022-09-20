package provider.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: RestTemplateConfig
 * @projectName: erukeribbon
 * @description: restTemplateConfig
 * @date: 2022-07-04 11:21
 **/
@Component
public class RestTemplateConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(30 * 1000);
        return new RestTemplate(requestFactory);
    }
}
