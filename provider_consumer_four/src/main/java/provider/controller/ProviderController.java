package provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProviderController {

    @Value("${server.port}")
    private String port;
    @Autowired
    private RestTemplate restTemplate;

    //    @GetMapping("/port")
    public String getPotValue() {
        ResponseEntity<String> result = restTemplate.getForEntity("http://provider/port", String.class);
        return result.getBody();
    }
}
