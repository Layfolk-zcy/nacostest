package com.feign;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: MyFeign
 * @projectName: erukeribbon
 * @description: feign
 * @date: 2022-08-11 11:27
 **/
/*@FeignClient("PROVIDER-CONSUMER")*/
public interface MyFeign {

    @GetMapping("/test")
    String test();
}
