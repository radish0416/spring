package com.gf.consumer.security.aspect;

import com.gf.api.entity.BaseRequestTokenBean;
import com.gf.api.entity.Result;
import com.gf.company.utils.TokenUtil;
import com.gf.consumer.exception.ConsumerLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 根据token判断账号是否存在，存在即登录成功
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/7/4
 */
@Slf4j
@Component
@Aspect   //切片注解
public class LoginAspect {

    /**
     * 对于consumer层只有一些需要登录后请求的接口，我们将他们放在同一层面上，采用切片处理
     * 自定义的NeedLogin注解：com.shule.cloud.ordering.consumer_service.common.annotation.NeedLogin
     * 在需要登录才能访问的接口上面添加，然后用户请求时，携带的token会直接在这里进行校验，判断用户是否登录了
     * 没有登录就直接抛异常，在全局异常处理ConsumerExceptionHandler那里捕获返回给前台
     */
    @Autowired
    private TokenUtil tokenUtil;

    @Pointcut("@annotation(com.gf.consumer.security.annotation.NeedLogin)")
    public void isLoginVerifyByToken() {
    }
    @Before("isLoginVerifyByToken() && args(baseRequestTokenBean)")
    public void notifyRedisClearDishes(JoinPoint joinPoint, BaseRequestTokenBean baseRequestTokenBean) {
        String token = baseRequestTokenBean.getToken();
        Result result = tokenUtil.hasLogin(token);
        if(result.getCode() != 105){
            throw new ConsumerLoginException(result.getCode(),result.getMsg());
        }
    }

}

