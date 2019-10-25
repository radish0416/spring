package com.gf.company.security.service;

import com.gf.api.AuthEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Liu Jinglei
 * @version 1.0
 * 决策器 负责拦截请求，对用户权限
 * @Date Created in 2019/7/27
 */
@Slf4j
@Service
public class ShuleAccessDecisionManager implements AccessDecisionManager {

    /**
     * 用户请求接口时，该方法进行拦截并且进行权限校验
     * @param object  就是FilterInvocation对象，可以得到request等web资源
     * @param configAttributes 是获取的该节点所需要的权限列表，当configAttributes为空或者里面有AuthEnums.AUTH_WHITE权限时则直接通过，
     * @param authentication 包含了当前的用户信息，包括拥有的权限。这里的权限来源就是前面登录时UserDetailsService中设置的authorities。和configAttributes进行一一对比，如果有一个符合的话就直接通过，否则就拦截
     */

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {

        if(null== configAttributes || configAttributes.size() <=0) {
            return;
        }

        for(Iterator<ConfigAttribute> iter = configAttributes.iterator(); iter.hasNext(); ) {
            ConfigAttribute c = iter.next();
            String cAuth = c.getAttribute().trim();

            //判断该节点的权限是否是AuthEnums.AUTH_WHITE权限，是的话直接通过，不需要下一步的校验
            if(cAuth.equals(AuthEnums.AUTH_WHITE.toString())){
                return;
            }

            //对权限进行一一对比
            for(GrantedAuthority ga : authentication.getAuthorities()) {
                if(cAuth.equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("AccessDenied  need authentication.");

    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}

