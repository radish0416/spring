package com.gf.api.entity;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/7/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户包含权限等的信息")
public class ShuleUserDetails implements UserDetails {

    private String token ;

    @ApiModelProperty("登录用户的类型，1：门店员工，0：企业管理员")
    private int userType = 0;

    private int id ;

    private String username;

    private String password;

    private Set<? extends GrantedAuthority> authorities;

    private Integer company_id;

    private Integer dept_id;

    private String employ_name;

    private String mobile_inf;

    private String email;

    private String auth;

    private String rec_sts;

    private Integer status;

    private String last_login_ip;

    private String sex;

    private String logo;

    private String id_card;

    private Integer class_id;

    private String class_name;

    private String account_id;

    private String ieme="";

    private String android_token="";

    private Double count;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.password;
    }

    @JsonIgnore
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 指示账号是否过期、无效
     * 过期、无效帐户无法进行“身份验证”
     * 默认：	true 有效，未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指示账号是否已被锁定
     * 已锁定账号无法进行“身份验证”
     * 默认：true 未锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示账号的凭据(密码)是否已过期。过期凭据会阻止身份验证。
     * 默认：true 未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 指示账号是否已禁用。不能对禁用用户进行身份验证。
     * 默认： true 未禁用，处于可用状态
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

