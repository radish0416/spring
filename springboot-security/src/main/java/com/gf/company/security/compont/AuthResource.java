package com.gf.company.security.compont;



import com.gf.api.AuthEnums;
import com.gf.api.RoleEnums;

import java.lang.annotation.*;

/**
 * 自定义的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface AuthResource {

    String resCode() ;
    String resName() ;
    RoleEnums[] roleCode();
    AuthEnums authCode();
    String roleName();

//	boolean isMenu() default false;
}