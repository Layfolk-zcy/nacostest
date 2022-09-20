package provider.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: FeignController
 * @projectName: erukeribbon
 * @description: feign
 * @date: 2022-08-03 21:58
 **/
/*如果同时指定name和url属性: 则以url属性为准,name属性指定的值便当做客户端的名称*/
@FeignClient(value = "PROVIDERS")
public interface FeignController {

    @GetMapping("/getPotValueProviderTwo")
    public String getPotValueProviderTwo();
}
