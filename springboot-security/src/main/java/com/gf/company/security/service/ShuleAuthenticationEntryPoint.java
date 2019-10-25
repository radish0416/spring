package com.gf.company.security.service;


import com.gf.api.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 匿名用户访问无权限资源时的异常
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/7/27
 */
@Slf4j
@Component
public class ShuleAuthenticationEntryPoint  implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");

//        response.setStatus(401);
        Result result = Result.authorityError("");
        log.debug("@@@@@@@@@@@@@  ShuleAuthenticationEntryPoint  result -->"+result.toJsonString());
        response.getWriter().write(result.toJsonString());
        response.flushBuffer();
    }

}

