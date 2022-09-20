package com.controller;

import cn.hutool.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: TestController
 * @projectName: erukeribbon
 * @description: test controller
 * @date: 2022-08-01 16:20
 **/
@RestController
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private FeignController feignController;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired(required = false)
    private RestTemplate restTemplate;

    @GetMapping("/gatewayTest")
    public String gatewayTest() {
       /* //第三步 具体调用方式    这里的“lb”也可以换成http
        Mono<Object> toMono = webBuilder.baseUrl("lb://服务名/").build().get().uri(uriBuilder ->
                uriBuilder.path("/api/oauth/check_token").queryParam("参数名称", "参数值").build()
        ).header(HttpHeaders.AUTHORIZATION, token).exchangeToMono(clientResponse -> clientResponse.bodyToMono(Object.class));
        // 不调用subscribe或者block是不会调用服务的
        Object block = toMono.subscribe();*/

        /*Mono<Object> toMono = webClientBuilder.baseUrl("lb://PROVIDER-CONSUMER/").build().get().uri(uriBuilder ->
                uriBuilder.path("/consumerTest").build()).exchangeToMono(clientResponse -> clientResponse.bodyToMono(Object.class));
        // 不调用subscribe或者block是不会调用服务的
        toMono.flatMap(Mono::just).subscribe();*/
        /*String forObject = restTemplate.getForObject("lb://PROVIDER-CONSUMER/consumerTest", String.class);
        System.out.println(forObject);*/
        try {
            CompletableFuture<JSONObject> result = CompletableFuture.supplyAsync(() -> {
                HttpHeaders headers = new HttpHeaders();
                MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
                headers.setContentType(type);
                headers.add("Accept", MediaType.APPLICATION_JSON.toString());

                Map<String, String> requestMap = new HashMap<>();
                requestMap.put("name", "ldj");
                requestMap.put("age", "15");

                HttpEntity<Map> entity = new HttpEntity<>(requestMap, headers);
                JSONObject jsonObject = restTemplate.postForObject("http://PROVIDER-CONSUMER" + "/entity", entity, JSONObject.class);
                return jsonObject;
            });
            JSONObject jsonObject = result.get();
            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "eeee";
        /*TestBean tesktBean = new TestBean();
        JSONObject jsonObject = feignController.testMethod(tesktBean);
        return jsonObject.toString();*/
    }

    @GetMapping("/dir/test")
    public String gatewayMethod() {
        logger.info("output message validate");
        throw new RuntimeException("test异常");
        /*return "gateway method fallback";*/
    }

   /* @GetMapping("/simpleManual")
    public Mono<String> simpleManual() {
        return Mono.just("hello").map(String::toUpperCase).doOnEach(WebFluxSleuthOperators
                .withSpanInScope(SignalType.ON_NEXT, signal -> logger.info("Hello from simple [{}]", signal.get())));
    }*/
}
