package com.gf.company.utils;


import com.gf.api.RandomUtil;
import com.gf.api.entity.ComResponseData;
import com.gf.api.RedisHead;
import com.gf.api.entity.ShuleUserDetails;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created with IDEA
 * aor:nick.niu
 * Date:2019/5/28
 * Time:13:20
 */
@Slf4j
@Component
public class UtilInfo {
    @Autowired
    private RedisTemplate redisTemplate;


    private static final String ALL_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    //登陆成功后调用，将TOKEN返回给客户端
    public  String setLogonToken(String token){
        String tmpstr = "";
        if (StringUtils.isEmpty(token)){
            tmpstr = RandomUtil.getRandomNumWordStr(20);
        }else{
            tmpstr = token;
        }
        return tmpstr;
    }
    /*
    根据输入的TOKEN判断用户是否登陆
     */
    public boolean checkToken(String token,String flag){
        boolean result = false;
        try{
            if ( redisTemplate.opsForValue().get(RedisHead.company_user_login+token)!=null){
                result = true;
                redisTemplate.opsForValue().set(RedisHead.company_user_login+token,flag,30, TimeUnit.MINUTES);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /*
    短信发送
     */
//    public boolean setSMS(String sms,String token){
//        boolean result = false;
//        try{
//           redisTemplate.opsForValue().set(RedisHead.sms+token,sms,5,TimeUnit.MINUTES);
//        }
//        catch(Exception e){
//           e.printStackTrace();
//        }
//        return result;
//    }

    /**
     * 仅用于测试
     * @author Liu Jinglei
     * @Date 2019/5/31
     * @params [token]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    /*public Map<String,Object> tokenForCompanyInfo(String token){

        String key = RedisHead.companyinfo + token;
        if(!redisTemplate.hasKey(key)){
            redisTemplate.opsForSet().add(key,companyMap());
        }
        Set<Map<String,Object>> set = redisTemplate.opsForSet().members(key);

        return (Map<String,Object>)set.toArray()[0];
    }
    public Map<String,Object> companyMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("company_id",11);
        map.put("dep_id",1);
        map.put("user_id",1);
        return map;
    }*/


    /**
     * 设置用户企业信息到redis内
     * @params [comResponseData, token]
     * @return java.lang.String
     * @version 1.0
     */
    public String setUserCompanyInfoToRedis(ComResponseData comResponseData,String token){
        String key = RedisHead.company_user_login+token;
        redisTemplate.opsForHash().put(key,RedisHead.COMPANY_USER_INFO_HASHKEY,comResponseData);
        redisTemplate.expire(key,RedisHead.TIMEOUT_DEFAULT_24_HOURS,TimeUnit.HOURS);
        return token;
    }

    /**
     * 保存商户验证码信息到redis中，时间为5分钟
     * @param comResponseData
     * @param token
     * @return
     */
    public String setCompanySMSInfoToRedis(ComResponseData comResponseData,String token){
        String key = RedisHead.company_user_login+token;
        redisTemplate.opsForHash().put(key,RedisHead.COMPANY_USER_INFO_HASHKEY,comResponseData);
        redisTemplate.expire(key,RedisHead.TIMEOUT_SMS_MINUTES,TimeUnit.MINUTES);
        return token;
    }

    /**
     * 设置用户的企业信息，以及用户自身的权限信息
     * @params [comResponseData, authentication, token]
     * @return void
     * @version 1.0
     */
    public void setUserAuthCompanyToRedis(ComResponseData comResponseData, Authentication authentication, String token){
        String key = RedisHead.company_user_login+token;

        Map redis = new HashMap<>();
        redis.put(RedisHead.COMPANY_USER_INFO_HASHKEY,comResponseData);
        if(authentication!=null){
            redis.put(RedisHead.COMPANY_USER_AUTHENTICATION_HASHKEY,authentication);
        }
        redisTemplate.opsForHash().putAll(RedisHead.company_user_login + token,redis);

        redisTemplate.expire(key,RedisHead.TIMEOUT_DEFAULT_24_HOURS,TimeUnit.HOURS);
    }

    //更新用户信息到redis中
    public void setUserCompanyToRedis(ComResponseData comResponseData,String token){
        String key = RedisHead.company_user_login+token;
        Map redis = new HashMap<>();
        redis.put(RedisHead.COMPANY_USER_INFO_HASHKEY,comResponseData);
        redisTemplate.opsForHash().putAll(RedisHead.company_user_login + token,redis);
        redisTemplate.expire(key,RedisHead.TIMEOUT_DEFAULT_24_HOURS,TimeUnit.HOURS);
    }

    /**
     * 从redis内查询用户对应的企业信息
     * @params [token]
     * @return com.bean.response.company.ComResponseData
     * @version 1.0
     */
    public ComResponseData getCompanyInfoByToken(String token){
        Map<String,Object> map = getUserAndCompanyByToken(token);
        if(CollectionUtils.isEmpty(map)){
            return null;
        }
        ComResponseData data= (ComResponseData) map.get(RedisHead.COMPANY_USER_INFO_HASHKEY);
        return data;
    }

    /**
     * 从上下文获取token
     * @params []
     * @return com.shule.springcloud.entities.security.ShuleUserDetails
     * @version 1.0
     */
    public ShuleUserDetails getUserDetailsFromSecurity(String token){
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(obj instanceof String) return null;
        ShuleUserDetails userDetails = (ShuleUserDetails)obj;
        if(userDetails != null && !StringUtils.isEmpty(userDetails.getToken()) && userDetails.equals(token)) {
            return userDetails;
        }else{
            return null;
        }
    }


    public Map<String,Object> getUserAndCompanyByToken(String token){
        Map<String,Object> map = new HashMap<>();
        String tt = RedisHead.company_user_login+token;
        try{
            map = redisTemplate.opsForHash().entries(tt);
        }catch (Exception e){
            log.debug("错误----------------------------------------");
            e.printStackTrace();
        }
        return map ;
    }

    public Authentication getUserAuthenticationByToken(String token){
        Map<String,Object> map = getUserAndCompanyByToken(token);
        if(CollectionUtils.isEmpty(map)){
            return null;
        }
        Authentication authentication = (Authentication) map.get(RedisHead.COMPANY_USER_AUTHENTICATION_HASHKEY);
        return authentication;
    }
}
