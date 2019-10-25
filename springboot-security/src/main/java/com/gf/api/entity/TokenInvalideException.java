package com.gf.api.entity;

import org.springframework.security.core.AuthenticationException;

/**
 * 无效的token
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/7/30
 */
public class TokenInvalideException extends AuthenticationException {
    public TokenInvalideException(String msg) {
        super(msg);
    }

    public TokenInvalideException(String msg, Throwable t) {
        super(msg, t);
    }
}
