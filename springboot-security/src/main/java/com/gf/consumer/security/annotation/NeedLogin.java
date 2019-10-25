package com.gf.consumer.security.annotation;

import java.lang.annotation.*;

/**
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/7/4
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedLogin {
}
