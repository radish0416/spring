package com.gf.api.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;

/**
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/7/26
 */
public class ShuleGrantedAuthority implements GrantedAuthority {
    private static final long serialVersionUID = -3004632358563141555L;

    @NotNull
    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public ShuleGrantedAuthority(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
