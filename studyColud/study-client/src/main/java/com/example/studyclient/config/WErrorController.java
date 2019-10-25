package com.example.studyclient.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 全局异常抓取处理类
 * @author Liu Jinglei
 * @Date 2019/5/27
 * @version 1.0
 */
@Slf4j
@RestController
public class WErrorController extends BasicErrorController{

    public WErrorController(){
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    private static final String PATH = "/error";
   /**
    * controller层error跳转处理，指定返回json格式数据
    * 返回信息
    * {
    *     "timestamp": 1559102470479,
    *     "status": 500,
    *     "error": "Internal Server Error",
    *     "message": "存放异常信息",
    *     "path": "请求路径"
    * }
    * @params
    * @return
    */
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);
       log.debug("======== error controller status->"+status);
       log.debug("======== error controller body->"+body);
        return new ResponseEntity<Map<String, Object>>(body, status);
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
