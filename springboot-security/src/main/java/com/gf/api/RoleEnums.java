package com.gf.api;
/**
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/7/31
 */
public enum  RoleEnums {

    ROLE_COMPANY_ADMIN("公司管理员"),
    ROLE_DEPART_MANAGER("店长"),
    ROLE_DEPART_WAITER("服务员"),
    ROLE_DEPART_CASHIER("收银员"),
    ROLE_WHITE("白名单，不进行权限认证");

    private String value ;

    RoleEnums(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

