package com.swee.model.core.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(exclude = {WebMvcAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class SweeGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SweeGatewayApplication.class, args);
        System.out.println("gateway");
    }

}
