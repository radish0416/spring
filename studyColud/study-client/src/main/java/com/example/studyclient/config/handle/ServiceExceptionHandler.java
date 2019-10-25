package com.example.studyclient.config.handle;

import com.bean.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

@Slf4j
@RestControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Result nullPointerExceptionHandler(NullPointerException exception){

        return Result.formatException(exception);
    }


    @ExceptionHandler(HttpServerErrorException.class)
    @ResponseBody
    public Result httpServerErrorExceptionHandler(HttpServerErrorException exception){

        return Result.formatException(exception);
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseBody
    public Result illegalStateExceptionExceptionHandler(IllegalStateException exception){

        return Result.formatException(exception);
    }
}
