package com.gf.company.security.service;


import com.gf.api.entity.Result;
import com.gf.api.entity.TokenInvalideException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户权限认证登录失败
 */
@SuppressWarnings("ALL")
@Slf4j
@Component
public class ShuleAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        Result result = Result.needAuthority("");
        if(e instanceof UsernameNotFoundException
                || e instanceof TokenInvalideException
                || e instanceof BadCredentialsException){
            result = Result.format(Result.PARAM_ERROR,e.getMessage());
        }

        log.debug("@@@@@@@@@@@@@  ShuleAuthenticationFailureHandler  result -->"+result.toJsonString());

        response.getWriter().write(result.toJsonString());
        response.flushBuffer();
    }

}

