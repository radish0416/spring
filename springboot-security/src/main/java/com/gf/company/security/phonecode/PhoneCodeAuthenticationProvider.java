package com.gf.company.security.phonecode;

import com.gf.api.entity.ComResponseData;
import com.gf.api.entity.ShuleUserDetails;
import com.gf.api.entity.TokenInvalideException;
import com.gf.company.security.service.ShuleUserDetailsServiceImpl;
import com.gf.company.utils.UtilInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/7/26
 */
@Slf4j
public class PhoneCodeAuthenticationProvider implements AuthenticationProvider {

    private ShuleUserDetailsServiceImpl userDetailsService;

    private UtilInfo utilInfo;

    public void setUserDetailsService(ShuleUserDetailsServiceImpl userDetailsService){
        this.userDetailsService = userDetailsService;
    }
    public void setUtilInfo(UtilInfo utilInfo){
        this.utilInfo = utilInfo;
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        log.debug("@@@@@@@@@@@@ PhoneCodeAuthenticationProvider  authenticate  ");
        String phone = (String) auth.getPrincipal();
        Object credentials = auth.getCredentials();
        if(credentials instanceof String){
            return null;
        }

        Map map = (Map) auth.getCredentials();

        String token = (String) map.get("token");
        String code = (String) map.get("code");

        ComResponseData tokenData = utilInfo.getCompanyInfoByToken(token);

        if(tokenData == null || StringUtils.isEmpty(token)){
            throw new TokenInvalideException("获取验证码无效");
        }

        if(StringUtils.isEmpty(tokenData.getComVerSms()) ||  !code.equals(tokenData.getComVerSms())){
            throw new BadCredentialsException("输入验证码不正确，请重新登录");
        }
        log.debug("@@@@@@@@@@@@ PhoneCodeAuthenticationProvider  authenticate  phone "+phone);
        log.debug("@@@@@@@@@@@@ PhoneCodeAuthenticationProvider  authenticate  code "+code);

        ShuleUserDetails user = userDetailsService.loadUserByUsername(phone);
        user.setToken(token);
        if(user == null){
            throw new BadCredentialsException("输入手机号不正确，请重新登陆！");
        }


        return new UsernamePasswordAuthenticationToken(user, token, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> auth) {
        return true;
    }

}

