package com.example.ms_rest_mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsRestMongoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsRestMongoApplication.class, args);
    }
}


