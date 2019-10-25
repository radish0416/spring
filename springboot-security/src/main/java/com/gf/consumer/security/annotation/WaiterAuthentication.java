package com.gf.consumer.security.annotation;

import com.gf.api.AuthEnums;

import java.lang.annotation.*;

/**
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/8/2
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WaiterAuthentication {
    AuthEnums authCode() ;
}
