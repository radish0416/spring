package com.gf.api;

/**
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/8/1
 */
public enum AuthEnums {

    AUTH_COMPANY_BUSINESS("餐饮系统营业数据"),
    AUTH_COMPANY_DEPART("餐饮系统门店管理"),
    AUTH_COMPANY_DISH("餐饮系统菜品管理"),
    AUTH_COMPANY_PAY("餐饮系统支付管理"),
    AUTH_COMPANY_MEMBER("餐饮系统会员管理"),
    AUTH_COMPANY_INVENTORY("餐饮系统库存管理"),
    AUTH_COMPANY_SYS_CONFIG("餐饮系统系统设置"),

    AUTH_WAITER_OPEN_ORDER("服务员APP开单"),
    AUTH_WAITER_CASHIER("服务员APP收银"),
    AUTH_WAITER_LINE("服务员APP排队"),
    AUTH_WAITER_LINK_QR("服务员APP关联二维码"),
    AUTH_WAITER_COPY_DISH("服务员APP拍照录菜"),

    AUTH_COMMENT_DEPART("点评门店管理"),
    AUTH_COMMENT_MANAGE("点评管理"),
    AUTH_COMMENT_FINANCIAL("点评财务管理管理"),

    AUTH_DEFAULT("默认登录后的权限"),
    AUTH_WHITE("白名单，不进行权限认证");

    private String value ;

    AuthEnums(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
