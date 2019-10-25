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
@ApiModel(value = "通过密码登录请求")
public class ComLoginPwdReq {
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "密码")
    private String pwd;

    @ApiModelProperty(value = "IEME号")
    private String ieme;


    @ApiModelProperty(value = "安卓推送Token")
    private String androidToken;
}
