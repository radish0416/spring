package com.example.studyclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile({"dev","test"})
public class Swagger2 {
    @Value("${swagger.host}")
    private String swaggerHost;
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //打包时打开  .host(swaggerHost)
                .host(swaggerHost)
                .apiInfo(apiInfo())
//                .pathMapping("/")
//                .directModelSubstitute(Date.class,String.class)
                .select()   // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("com.example.studyclient.WUser.WController"))  //生成该目录结构下的接口文档
                .paths(PathSelectors.any())  // 对所有api进行监控
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("学习springcloud APIs")
                .contact("殊乐学习")
                .version("1.0")
                .build();
    }
}
