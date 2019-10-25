package com.gf.api.entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IDEA
 * author:WuHeng
 * Date:2019/6/4 0004
 * Time:下午 8:58
 */
@Data
@ApiModel(value = "商户通过验证码登录请求")
public class ComLoginCodeReq{
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "验证码")
    private String verifyCode;
    @ApiModelProperty(value = "token必传")
    private  String  token;
}