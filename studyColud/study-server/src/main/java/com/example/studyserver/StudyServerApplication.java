package com.example.studyserver;

import com.spring4all.mongodb.EnableMongoPlus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableMongoPlus
@EnableCaching    //开启缓存功能
public class StudyServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyServerApplication.class, args);
    }

}
