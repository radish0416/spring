package com.gf.company.utils;

import com.gf.api.RedisHead;
import com.gf.api.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * redis内处理token类
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/6/13
 */
@Component
@Slf4j
public class TokenUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    private static final String DAFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm";

    /**
     * 验证是否登录
     * @param token
     * @return -5 token错误
     */
    public Result hasLogin(String token){
        Result result = Result.tokenError();
        return result;
    }
}

