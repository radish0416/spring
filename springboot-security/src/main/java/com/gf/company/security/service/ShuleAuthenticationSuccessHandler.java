package com.gf.company.security.service;


import com.gf.api.entity.ComResponseData;
import com.gf.api.entity.DeptListResponseData;
import com.gf.api.entity.Result;
import com.gf.api.entity.ShuleUserDetails;
import com.gf.company.test.service.CompanyBaseInfoService;
import com.gf.company.utils.UtilInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/7/27
 * 用户权限登录成功后的处理
 */
@Slf4j
@Component
public class ShuleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private CompanyBaseInfoService infoService;
    @Autowired
    private UtilInfo utilInfo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        Result result = Result.needAuthority("");

        String uri = request.getRequestURI();
        log.debug("@@@@@@@@@@@@@@@@@   SavedRequestAwareAuthenticationSuccessHandler  uri:"+uri);

        ShuleUserDetails userDetails = (ShuleUserDetails)authentication.getPrincipal();
        //生成token
        String token_gener  = utilInfo.setLogonToken(null) + "_" + System.currentTimeMillis();
        if(!StringUtils.isEmpty(userDetails.getToken())){
            token_gener = userDetails.getToken();
        }else{
            userDetails.setToken(token_gener);
        }

        ComResponseData<List<DeptListResponseData>> tmp = null;
//       返回的企业用户或者员工登录信息
        tmp = infoService.getCompanyByUser(userDetails);
        utilInfo.setUserAuthCompanyToRedis(tmp,authentication,token_gener);
        //将用户信息和token一并返回前台
        result = Result.formatRet(tmp,token_gener);

        response.getWriter().write(result.toJsonString());
        response.flushBuffer();
    }

}
