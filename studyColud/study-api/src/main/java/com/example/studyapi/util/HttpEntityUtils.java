package com.example.studyapi.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * 封装供RestTemplate使用的HttpEntity
 * @author Liu Jinglei
 * @Date Created in 2019/5/31
 */
public class HttpEntityUtils {
    /**
     * 封装HttpEntity
     * 设置HttpHeader的type类型为JSON
     * @params [vo]
     * @return org.springframework.http.HttpEntity<java.lang.Object>
     */
    public static HttpEntity<Object> packageSendJSON(Object vo){
        HttpHeaders headers = new HttpHeaders();//request header
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> data = new HttpEntity<Object>(vo, headers);
        return data;
    }
}
