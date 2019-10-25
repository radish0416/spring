package com.gf.consumer.exception;

import com.gf.api.entity.Result;

/**
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/8/2
 */

public class WaiterAccessException extends RuntimeException{
    protected int code = Result.AUTHORITIES_ERROR;

    protected String message;


    public WaiterAccessException() {
        message = Result.AUTHORITIES_ERROR_MSG;
    }

    public WaiterAccessException(String message) {
        this.message = message;
    }
}