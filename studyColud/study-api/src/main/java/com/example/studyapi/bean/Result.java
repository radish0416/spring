package com.example.studyapi.bean;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:Li Xianjiang
 * Date:2019/10/30
 * Time:10:33
 * 定义所有接口返回体
 */
public class Result<T> implements Serializable {
    public static final int success_code = 1;
    public static final String success_msg = "success";

    public static final String DATA_STATUS_OK="OK";

    public static final int server_error = 0;
    public static final String server_error_msg = "服务器错误";
    public static final int server_exception = -1;
    public static final String server_exception_msg = "服务器异常";
    public static final int empty_param = -2;
    public static final String empty_param_msg = "param can not be null";
    public static final int param_error = -3;
    public static final String param_error_msg = "非法的输入";

    public static final int param_is_empty = -4; //参数为空

    public static final int TOKEN_ERROR = -5;//token错误
    public static final String  TOKEN_ERROR_MSG = "token错误";

    public static final int PARAM_ERROR = -6;//传入的参数错误
    public static final String  PARAM_ERROR_MSG = "传入的参数错误";

    public static final int INIT_RESULT = -7;//Result初始化
    public static final String  INIT_RESULT_MSG = "Result初始化";

    public static final int OPER_FAIL = -8;//操作失败
    public static final String  OPER_FAIL_MSG = "操作失败";
    public static final int  AUTHORITIES_ERROR= 401;
    public static final String  AUTHORITIES_ERROR_MSG = "授权失败,请重新登录";
    public static final int  NEED_AUTHORITIES= 402;
    public static final String  NEED_AUTHORITIES_MSG = "权限不足，请联系管理员分配权限";


    public static final String  PAY_FAIL = "交易失败";

    private int code = 1;
    private String msg = "success";
    private String token = "";//接口返回token
    private T data;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static <T> Result<T> resetToken(Result<T> result,String newToken){
        result.setToken(newToken);
        return result;
    }
    public static <T> Result<T> formatRet(T data) {
        Result result = new Result();
        result.setData(data);
//        System.out.println("====  Result return data -->"+result.toJsonString());
        return result;
    }

    public static  Result OK() {
        Result result = new Result();
        result.setData(null);
        return result;
    }
    public static <T> Result<T> formatRet(T data,String token) {
        return new Result(data,token);
    }


    public static Result formatBussinessErrorEmpty(String msg) {
        Result ret = new Result();
        ret.setCode(param_is_empty);
        ret.setMsg(msg);
        return ret;
    }
    public static Result formatBussinessError(String msg) {
        Result ret = new Result();
        ret.setCode(server_error);
        ret.setMsg(msg);
        return ret;
    }


    public static Result format(int code, String msg) {
        Result ret = new Result();
        ret.setCode(code);
        ret.setMsg(msg);
        return ret;
    }

    /**
     * 通过token获取账号信息时，找不到相应的信息
     * @author Liu Jinglei
     * @Date 2019/6/10
     * @return com.bean.Result
     * @version 1.0
     */
    public static Result tokenError(){
        Result ret = new Result();
        ret.setCode(TOKEN_ERROR);
        ret.setMsg(TOKEN_ERROR_MSG);
        return ret;
    }
    public static Result initResult(){
        Result ret = new Result();
        ret.setCode(INIT_RESULT);
        ret.setMsg(INIT_RESULT_MSG);
        return ret;
    }
    public static Result authorityError(String appendStr){
        Result ret = new Result();
        ret.setCode(AUTHORITIES_ERROR);
        ret.setMsg(AUTHORITIES_ERROR_MSG+","+appendStr);
        return ret;
    }
    public static Result needAuthority(String appendStr){
        Result ret = new Result();
        ret.setCode(NEED_AUTHORITIES);
        ret.setMsg(NEED_AUTHORITIES_MSG+","+appendStr);
        return ret;
    }

    /**
     * 当传入的参数不符合要求时，返回参数错误提示
     * @author Liu Jinglei
     * @Date 2019/6/11
     * @params []
     * @return com.bean.Result
     * @version 1.0
     */
    public static Result paramError(){
        Result ret = new Result();
        ret.setCode(PARAM_ERROR);
        ret.setMsg(PARAM_ERROR_MSG);
        return ret;
    }


    public static Result formatBussinessFfError(String msg) {
        Result ret = new Result();
        ret.setCode(param_error);
        ret.setMsg(msg);
        return ret;
    }

    /**
     * 从map内读取code 和msg 信息
     * @author Liu Jinglei
     * @Date 2019/5/30
     * @params [map]
     * @return com.bean.Result
     */
    public static Result formatCodeMsg(Map map) {
        Result ret = new Result();
        ret.setCode((Integer)map.get("code"));
        ret.setMsg((String)map.get("msg"));
        return ret;
    }

    public static Result formatException(Exception exc) {
        Result ret = new Result();
        ret.setCode(server_exception);
        ret.setMsg(exc.getMessage());
        return ret;
    }

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public Result(T data,String token) {
        this.data = data;
        this.token = token;
    }

    public int getCode() {
        return this.code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return this.msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    public String toJsonString() {
        String jsonString = JSONObject.toJSONString(this);
        return jsonString;
    }

    public static String toJSONString(Object o) {
        return JSONObject.toJSONString(o);
    }

    public static <T> List<T> parseArray(String str, Class<T> clazz) {
        return StringUtils.isBlank(str) ? null : JSONObject.parseArray(str, clazz);
    }

}
