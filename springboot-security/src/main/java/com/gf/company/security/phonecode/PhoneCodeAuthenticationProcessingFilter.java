package com.gf.company.security.phonecode;

import com.alibaba.fastjson.JSON;
import com.gf.api.entity.ComLoginCodeReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/7/26
 */

/**
 * 用户验证码登录和用户密码登录的原理一致
 */
@SuppressWarnings("ALL")
@Slf4j
public class PhoneCodeAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "phone";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "code";
    public static final String SPRING_SECURITY_FORM_TOKEN_KEY = "token";
    private String usernameParameter = "phone";
    private String passwordParameter = "code";
    private String tokenParameter = "token";
    private boolean postOnly = true;

    public PhoneCodeAuthenticationProcessingFilter() {
        super(new AntPathRequestMatcher("/apis/company/loginByCode", "POST"));
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {

            ComLoginCodeReq loginCodeReq = obtainUserInfo(request);
            String phone = loginCodeReq.getPhone();
            String code = loginCodeReq.getVerifyCode();
            String token = loginCodeReq.getToken();
            log.debug("@@@@@@@@@@@@  PasswordAuthenticationProcessingFilter  obtain phone is " + phone);
            log.debug("@@@@@@@@@@@@  PasswordAuthenticationProcessingFilter  obtain code is " + code);
            log.debug("@@@@@@@@@@@@  PasswordAuthenticationProcessingFilter  obtain token is " + token);
            if (phone == null) {
                phone = "";
            }

            if (code == null) {
                code = "";
            }
            Map map = new HashMap<>();
            map.put("token",token);
            map.put("code",code);
            phone = phone.trim();
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(phone, map);
            this.setDetails(request, authRequest);
            AuthenticationManager authenticationManager = (AuthenticationManager) this.getAuthenticationManager();
            return authenticationManager.authenticate(authRequest);
        }
    }

    protected ComLoginCodeReq obtainUserInfo(HttpServletRequest request) {
        InputStreamReader reader = null;
        ComLoginCodeReq loginCodeReq = null;
        try {
            reader = new InputStreamReader(request.getInputStream(), "UTF-8");

            char[] buff = new char[2048];
            int length = 0;
            String result = null;
            while ((length = reader.read(buff)) != -1) {
                result = new String(buff, 0, length);
            }
            loginCodeReq = JSON.parseObject(result, ComLoginCodeReq.class);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return loginCodeReq;
        }
    }


/*    protected String obtainPassword(HttpServletRequest request) {
        String pwd = request.getParameter(this.passwordParameter);
        log.debug("@@@@@@@@@@@@  PasswordAuthenticationProcessingFilter  obtainPassword is " + pwd);
        return pwd;
    }

    protected String obtainUsername(HttpServletRequest request) {
        String userName = request.getParameter(this.usernameParameter);
        log.debug("@@@@@@@@@@@@  PasswordAuthenticationProcessingFilter  obtainUsername is " + userName);
        return userName;
    }*/

    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setUsernameParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
        this.usernameParameter = usernameParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
        Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
        this.passwordParameter = passwordParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getUsernameParameter() {
        return this.usernameParameter;
    }

    public final String getPasswordParameter() {
        return this.passwordParameter;
    }

    public String getTokenParameter() {
        return tokenParameter;
    }

    public void setTokenParameter(String tokenParameter) {
        this.tokenParameter = tokenParameter;
    }
}

