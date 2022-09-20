package provider.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"provider.**"})
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class ProviderServiceOne {
    public static void main(String[] args) {
        SpringApplication.run(ProviderServiceOne.class, args);
    }
}
