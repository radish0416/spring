package com.example.studyclient;

import com.example.studyclient.rule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker //断路器
@RibbonClient(name="STUDY_SERVER",configuration= MySelfRule.class)
public class StudyClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyClientApplication.class, args);
    }

}
