package com.gf.consumer.exception;

import com.gf.api.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/7/4
 * 全局处理异常事件
 */
@Slf4j
@RestControllerAdvice
public class ConsumerExceptionHandler {

    @ExceptionHandler(ConsumerLoginException.class)
    @ResponseBody
    public Result multipartExceptionHandler(ConsumerLoginException exception){

        return Result.format(exception.code,exception.message);
    }

    @ExceptionHandler(WaiterAccessException.class)
    @ResponseBody
    public Result waiterAccessExceptionHandler(WaiterAccessException exception){

        return Result.format(exception.code,exception.message);
    }

}

