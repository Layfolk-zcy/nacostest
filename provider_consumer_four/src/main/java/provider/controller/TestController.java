package provider.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import provider.common.annotation.AopAnnotation;

@RestController
@AopAnnotation
public class TestController {

    @Value("${server.port:}")
    private String port;

    @Value("${spring.application.name:}")
    private String name;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private FeignController feignController;


    @GetMapping("/consumerTest")
    @AopAnnotation
    public String consumerTest() {
//        return port;
        return feignController.getPotValueProviderTwo();
//        tracer.currentSpan().tag("provider_consumer_two", "value");
//        ResponseEntity<String> result = restTemplate.getForEntity("http://provider/port", String.class);
//        return result.getBody();
//        return "test";
    }

    @GetMapping("/portTest")
    public String portTest() {
        return port;
    }

    @PostMapping("/entity")
    public JSONObject testMethod(@RequestBody TestBean testBean) {
        testBean.setName(name);
        testBean.setPort(port);
        return JSONUtil.parseObj(testBean);
    }
}
