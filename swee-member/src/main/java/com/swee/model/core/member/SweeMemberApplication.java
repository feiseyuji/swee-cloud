package com.swee.model.core.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages={"com.swee.model.core"})
public class SweeMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(SweeMemberApplication.class, args);
        System.out.println("member");
    }
}
