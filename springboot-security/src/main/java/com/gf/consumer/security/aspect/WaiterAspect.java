package com.gf.consumer.security.aspect;

import com.gf.api.entity.BaseRequestTokenBean;
import com.gf.company.utils.WaiterUtil;
import com.gf.consumer.exception.WaiterAccessException;
import com.gf.consumer.security.annotation.WaiterAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * 根据token判断服务员账号是否存在，同时判断服务员是否有相应的操作权限
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/7/4
 */
@Slf4j
@Component
@Aspect
public class WaiterAspect {

    /**
     * 对于服务员登录有权限校验的，也采用切片处理
     * 自定义的WaiterAuthentication注解：com.shule.cloud.ordering.consumer_service.common.annotation.WaiterAuthentication
     * 在需要服务员相关权限才能访问的接口上面添加相应的权限，服务员登录后会检验相关权限是否拥有
     * 没有相关权限就直接抛异常，在全局异常处理WaiterAccessException那里捕获返回给前台
     */
    @Autowired
    private WaiterUtil waiterUtil;

    @Pointcut("@annotation(com.shule.cloud.ordering.consumer_service.common.annotation.WaiterAuthentication)")
    public void waiterAuthenticationByToken() {
    }

    @Before(value = "waiterAuthenticationByToken() && args(baseRequestTokenBean) && @annotation(waiterAuthentication)"
            ,argNames = "joinPoint,baseRequestTokenBean,waiterAuthentication")
    public void notifyRedisClearDishes(JoinPoint joinPoint, BaseRequestTokenBean baseRequestTokenBean, WaiterAuthentication waiterAuthentication) {
        String token = baseRequestTokenBean.getToken();
        String  authCode = waiterAuthentication.authCode().toString();

        //取出该服务员所拥有的权限
        Authentication authentication = waiterUtil.getWaiterAuthenticationFromRedis(token);

        if(authentication == null ){
            throw new WaiterAccessException("请重新登录");
        }

        boolean canNotAccess = true;

        for(GrantedAuthority ga : authentication.getAuthorities()) {
            if(authCode.equals(ga.getAuthority())) {
                canNotAccess = false;
            }
        }

        log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@   waiter aspect can not access "+canNotAccess);
        if(canNotAccess){
            throw new WaiterAccessException("没有相应的权限");
        }

    }

}
