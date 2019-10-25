package com.gf.company.security.phonepwd;


import com.alibaba.fastjson.JSON;
import com.gf.api.entity.ComLoginPwdReq;
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

/**
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/7/26
 */

/**
 * 用户密码登录的时候，直接在这里进行拦截，判定用户登录成功后再取出用户所拥有的的权限列表
 * 因为security自带的验证字段是usename和password字段，而这个项目传过来的是phone和pwd字段，所以要进行转换
 * attemptAuthentication方法是进行字段转换的，然后会在NamePwdAuthenticationProvider类中进行使用
 */
@SuppressWarnings("ALL")
@Slf4j
//@Component
public class PasswordAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "phone";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    private String usernameParameter = "phone";
    private String passwordParameter = "pwd";
    private boolean postOnly = true;

    public PasswordAuthenticationProcessingFilter() {
        super(new AntPathRequestMatcher("/apis/company/loginByPwd", "POST"));
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {

            ComLoginPwdReq loginPwdReq = obtainUserInfo(request);
            String username = loginPwdReq.getPhone();
            String password = loginPwdReq.getPwd();
            log.debug("@@@@@@@@@@@@  PasswordAuthenticationProcessingFilter  obtain phone is " + username);
            log.debug("@@@@@@@@@@@@  PasswordAuthenticationProcessingFilter  obtain Password is " + password);
            if (username == null) {
                username = "";
            }

            if (password == null) {
                password = "";
            }

            username = username.trim();
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            this.setDetails(request, authRequest);
            AuthenticationManager authenticationManager = (AuthenticationManager) this.getAuthenticationManager();
            return authenticationManager.authenticate(authRequest);
        }
    }

    protected ComLoginPwdReq obtainUserInfo(HttpServletRequest request) {
        InputStreamReader reader = null;
        ComLoginPwdReq loginPwdReq = null;
        try {
            reader = new InputStreamReader(request.getInputStream(), "UTF-8");

            char[] buff = new char[2048];
            int length = 0;
            String result = null;
            while ((length = reader.read(buff)) != -1) {
                result = new String(buff, 0, length);
            }
            loginPwdReq = JSON.parseObject(result, ComLoginPwdReq.class);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return loginPwdReq;
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

}

