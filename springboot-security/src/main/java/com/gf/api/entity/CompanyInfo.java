package com.gf.api.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 企业用户注册bean
 */
public class CompanyInfo implements Serializable {
    private int id;

    private String mobile_inf;

    private String pwd;

    private String employ_name;

    private Double count;

    private String rec_sts;

    private String status;

    private String last_login_ip;

    private String logo;

    private String account_id;

}
