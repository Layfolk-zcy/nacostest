package provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/getPotValueProviderOne")
    public String getPotValueProviderOne() {
        /*throw new RuntimeException("error: " + port);*/
        return port;
    }
}
