package com.ratelimiting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.*")
public class RateLimitingApplication {

    public static void main(String[] args) {
        SpringApplication.run(RateLimitingApplication.class, args);
    }

}
