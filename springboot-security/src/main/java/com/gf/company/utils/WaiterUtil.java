package com.gf.company.utils;

import com.gf.api.RedisHead;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IDEA
 * aor:nick.niu
 * Date:2019/5/28
 * Time:13:20
 */
@SuppressWarnings("ALL")
@Slf4j
@Component
public class WaiterUtil {
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 从redis内获取服务员的权限信息
     * @params [token]
     * @return org.springframework.security.core.Authentication
     * @version 1.0
     */
    public Authentication getWaiterAuthenticationFromRedis(String token){
        Map<String,Object> map = getWaiterFromRedis(token);
        if(CollectionUtils.isEmpty(map)){
            return null;
        }
        Authentication authentication = (Authentication) map.get(RedisHead.WAITER_USER_AUTHENTICATION_HASHKEY);
        return authentication;
    }
    /**
     * 从redis内获取服务员的信息
     * @params [token]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @version 1.0
     * @Since 增加权限之后
     */
    public Map<String,Object> getWaiterFromRedis(String token){
        Map<String,Object> map = new HashMap<>();
        String tt = RedisHead.waiter_user_login+token;
        try{
            map = redisTemplate.opsForHash().entries(tt);
        }catch (Exception e){
            log.debug("错误----------------------------------------");
            e.printStackTrace();
        }
        return map ;
    }
}
