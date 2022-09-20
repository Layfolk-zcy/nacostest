package com.feign;

import org.springframework.web.bind.annotation.PostMapping;

/***
 *
 */
//@FeignClient("provider_one")
public interface ConsumerOneFeign {

    @PostMapping("")
    String decrease();
}
