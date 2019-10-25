package com.example.studyclient.config;

import com.shule.springcloud.entities.StaticFilePaths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/6/19
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Value("${file.upload-path}")
    private String filePath ;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler(StaticFilePaths.IMAGES_ACCESS_PATH+"**").addResourceLocations("file:"+filePath);
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


}
