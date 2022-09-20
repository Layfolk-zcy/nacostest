package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public String test() {
        ResponseEntity<String> result = restTemplate.getForEntity("http://provider/port", String.class);
        return result.getBody();
    }
}
