package com.gf.company.security.service;


import com.gf.api.entity.Result;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决认证过的用户访问无权限资源时的异常
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/7/27
 */
@Slf4j
@Component
public class ShuleAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");

        log.debug("=======================");
        Result result = Result.needAuthority("");
        log.debug("@@@@@@@@@@@@@  ShuleAccessDeniedHandler  result -->"+result.toJsonString());
        response.getWriter().write(result.toJsonString());
        response.flushBuffer();
    }

}

