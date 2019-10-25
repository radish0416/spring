package com.gf.company.security.phonepwd;


import com.gf.api.entity.ShuleUserDetails;
import com.gf.company.security.service.ShuleUserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Map;

/**
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/7/26
 */

/**
 * 在PasswordAuthenticationProcessingFilter类中进行字段转换后
 * 在这里可以直接在authenticate方法中使用，然后ShuleUserDetails user = userDetailsService.loadUserByUsername(userName);
 * 取出该用户信息再和输入的密码比较，判断是否登录成功
 */
@SuppressWarnings("ALL")
@Slf4j
public class NamePwdAuthenticationProvider implements AuthenticationProvider {

    private ShuleUserDetailsServiceImpl userDetailsService;

    private EurekaUserPasswordEncoder encoder;

    public void setUserDetailsService(ShuleUserDetailsServiceImpl userDetailsService){
        this.userDetailsService = userDetailsService;
    }
    public void setPasswordEncoder(EurekaUserPasswordEncoder encoder){
        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        log.debug("@@@@@@@@@@@@ NamePwdAuthenticationProvider  authenticate  ");
        String userName = (String) auth.getPrincipal();

        Object credentials = auth.getCredentials();
        if(credentials instanceof Map){
            return null;
        }
        String password = (String) auth.getCredentials();

        ShuleUserDetails user = userDetailsService.loadUserByUsername(userName);

        if(user == null){
            throw new BadCredentialsException("手机号不正确，请重新登陆！");
        }

        log.debug("@@@@@@@@@@@@ NamePwdAuthenticationProvider  authenticate  password "+password);
        log.debug("@@@@@@@@@@@@ NamePwdAuthenticationProvider  authenticate  user password "+user.getPassword());
//        EurekaUserPasswordEncoder encoder = new EurekaUserPasswordEncoder();
//        log.debug(encoder.encode(password));
//        TODO 此处后续需要加入密码加密验证
//        boolean isMatch = encoder.matches(password, user.getPassword());
        boolean isMatch = password.equals(user.getPassword());
        if (!isMatch) {
            throw new BadCredentialsException("密码不正确，请重新登陆！");
        }

        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> auth) {
        return true;
    }

}

