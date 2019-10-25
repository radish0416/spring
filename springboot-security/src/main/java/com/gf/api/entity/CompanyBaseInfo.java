package com.gf.api.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyBaseInfo implements Serializable {
    private Integer id;

    private Integer company_id;

    private Integer dept_id;

    private String mobile_inf;

    private String pwd;

    private String employ_name;

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

    private String ieme;
}
