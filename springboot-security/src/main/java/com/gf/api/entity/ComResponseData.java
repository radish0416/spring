package com.gf.api.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created with IDEA
 * author:WuHeng
 * Date:2019/5/30 0030
 * Time:下午 1:42
 * 商户注册登录返回体
 */
@Setter
@Getter
@ApiModel(value = "后台用户信息返回体")
public class ComResponseData<T> {
    @ApiModelProperty(value = "企业用户登录为企业id,员工登录为员工id")
    private Integer id;

    @ApiModelProperty(value = "0表示未登录，1标识已登录")
    private Integer isLogin;

    @ApiModelProperty(value = "员工登录后的企业id")
    private Integer company_id;

    @ApiModelProperty(value = "员工登录后的门店id")
    private Integer dept_id;

    @ApiModelProperty(value = "0企业用户，1表示员工")
    private String userType;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "姓名")
    private String employ_name;

    @ApiModelProperty(value = "员工头像")
    private String logo;

    @ApiModelProperty(value = "如果是企业用户,账户id为企业账户,如果是员工,账户id为门店账户")
    private String account_id;

    @ApiModelProperty(value = "商户后台系统注册验证码")
    private String comRegSms;

    @ApiModelProperty(value = "商户后台系统校验验证码")
    private String comVerSms;

    @ApiModelProperty(value = "返回分页数据")
    private PageListResponse<T> pageListResponse;

    @ApiModelProperty(value = "泛型")
    private T data;


}

