package com.gf.consumer.exception;


/**
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/7/4
 */
public class ConsumerLoginException extends RuntimeException  {

    protected int code;

    protected String message;


    public ConsumerLoginException() {
        code = -107;
        message = "获取账号信息失败,有可能没有登录";
    }

    public ConsumerLoginException(int code, String message) {
        this.code = code;
        this.message = message;
    }

}

