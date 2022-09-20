package provider.controller;

import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/providers")
public class ProviderController {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private FeignController feignController;

    @Value("${server.port}")
    private String port;

    public static HttpEntity<MultiValueMap<String, Object>> getEntity(MultiValueMap<String, Object> postParameters) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<MultiValueMap<String, Object>> formEntity = new HttpEntity<>(postParameters, headers);
        return formEntity;
    }

    @GetMapping("/webSocketTest")
    public String webSocketTest() {
        return port;
    }

    @GetMapping("/getPotValueProviderTwo")
    public String getPotValueProviderTwo() {
        /*throw new RuntimeException("error:"+port);*/
        return feignController.getPotValueProviderOne();
    }

    @GetMapping("/services")
    public List<ServiceInstance> getServices(String serviceId) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        Map<String, String> metadata = instances.get(0).getMetadata();
        return instances;
    }

    @GetMapping("/service")
    public List<ServiceBean> getServiceInfo(String serviceId) {

        List<ServiceBean> services = new ArrayList<>();
        try {
            Map<String, String> headers = new HashMap<>(4);
            headers.put("Content-Type", "application/json");
            headers.put("Accept", "application/json");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAll(headers);
            ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8084/apps", String.class, httpHeaders);
            if (entity.getStatusCodeValue() == 200) {
                //接口返回的结果
                JSONArray bodyArray = JSON.parseArray(entity.getBody());
                for (int i = 0; i < bodyArray.size(); i++) {
                    JSONObject result = (JSONObject) bodyArray.get(i);
                    String name = (String) result.get("name");
                }
            }
        } catch (Exception e) {
            services = new ArrayList<>();
        }
        return services;
    }

}
