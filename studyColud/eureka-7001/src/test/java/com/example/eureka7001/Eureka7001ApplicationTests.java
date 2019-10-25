package com.example.eureka7001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer// EurekaServer服务器端启动类,接受其它微服务注册进来
class Eureka7001ApplicationTests {

    public static void main(String[] args)
    {
        SpringApplication.run(Eureka7001Application.class, args);
    }

}
