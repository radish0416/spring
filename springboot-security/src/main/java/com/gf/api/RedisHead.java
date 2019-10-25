package com.gf.api;



/**
 * 用于标记REDIS头信息
 * Created with IDEA
 * author:nick.niu
 * Date:2019/5/28
 * Time:13:09
 */
public class RedisHead {

    //    APP消费者账号的app_token_head 的过期时间
    public static final int TIMEOUT_DAYS = 30;
    //    APP消费者账号的购物车的过期时间
    public static final int SHOPPING_CART_TIMEOUT_HOURS = 2;
    //    APP 消费者账号对应的店铺 app_consumer_dep_head 的过期时间
    public static final int APP_CONSUMER_DEP_TIMEOUT_DAYS = 3;
    public static final int APP_CONSUMER_DISH_TIMEOUT_HOURS = 10;
    //   服务员下的订单的过期时间
    public static final int ORDER_TIMEOUT_MINUTES = 60;
    //    短信超时时间
    public static final int TIMEOUT_SMS_MINUTES = 5;

    public static final int TIMEOUT_DEFAULT_24_HOURS = 24;

    public static final String ORDER_SOURCE_WAITER = "收银APP";


    //产生预警和估清的业务消息存入redis中
    public static final String BusinessNews = "BusinessNews_";

    //    company 用户redis
    public static final String COMPANY_USER_INFO_HASHKEY = "COMPANY_USER_INFO_HASHKEY";
    public static final String COMPANY_USER_AUTHENTICATION_HASHKEY = "COMPANY_USER_AUTHENTICATION_HASHKEY";

    /**
     * 商户后台系统注册验证码标头
     */
    public final static String COM_REG_SMS = "comRegSms";
    /**
     * 商户后台系统校验验证码标头
     */
    public final static String COM_VER_SMS = "comVerSms";

    //扫描店码生成订单的取餐编号
    public final static String DEP_ORDER_NUMBER = "DEP_ORDER_NUMBER";


    //    存入到redis中APP注册短信发送token标头
    public final static String app_reg_sms = "APP_REG_SMS_";
    //  验证手机号是否正确，手机号可能不存在系统内
    public final static  String app_phone_sms = "APP_PHONE_SMS_";

    //    存入到redis中APP认证短信发送token标头
    public final static String app_ver_sms = "APP_VER_SMS_";

    //存入到redis中企业用户登陆和商户后台发送短信后生成token的标头
    public final static String company_user_login = "COMPANY_USER_LOGIN_INFO_";

    //服务员登录token
    public final static String waiter_user_login = "WAITER_USER_LOGIN_INFO_";
    //    company 用户redis
    public static final String WAITER_USER_INFO_HASHKEY = "WAITER_USER_INFO_HASHKEY";
    public static final String WAITER_USER_AUTHENTICATION_HASHKEY = "WAITER_USER_AUTHENTICATION_HASHKEY";

    //企业统计信息
    public final static String companyinfo = "COMPANY_INFO_";

    //门店统计信息
    public final static String company_dep_info = "COMPANY_DEP_INFO_";

    //  ==========================              店铺、菜品相关信息                ======================================
    //    redis内店铺信息 头
    public final static String dep_head = "DEP_HEAD_";
    //    redis内菜品相关信息 头
    public final static String dep_dish_relative_head = "DEP_DISH_RELATIVE_HEAD_";
    //  菜品详情头 + 菜品ID 组成完整的hashkey
    public static final String DISH_DETAIL_HASHKEY_HEAD = "DISH_DETAIL_HASHKEY_";
    //    菜品二级分类list头
    public static final String DISH_SECOND_TYPE_LIST_HASHKEY = "DISH_SECOND_TYPE_LIST_HASHKEY";
    //    根据菜品分类ID进行划分，保存分类下的所有菜品的list
    public static final String DISH_MAP_BY_SECOND_TYPE_HASHKEY = "DISH_MAP_BY_SECOND_TYPE_HASHKEY";
    //    店铺推荐菜列表
    public static final String DISH_RECOMMAND_MAP_HASHKEY = "DISH_RECOMMAND_MAP_HASHKEY";
    //    店铺所有菜品列表
    public static final String DISH_ALL_MAP_HASHKEY = "DISH_ALL_MAP_HASHKEY";


    //  APP首页 存放在redis  set集合内的key
    public final static String app_main_page_hash_key = "APP_MAIN_PAGE_HASH_KEY";
    //  APP token 开头
    public final static String app_token_head = "APP_TOKEN_HEAD_";
    //  APP 消费者账号 HK
    public final static String app_consumer_hash_key = "APP_CONSUMER_HASH_KEY";
    //   APP 消费者账号对应的店铺 开头
    public final static String app_consumer_dep_head = "APP_CONSUMER_DEP_HEAD_";

    //  桌码的购物车信息
    public final static String APP_TABLE_SHOPPING_CART_HEAD = "APP_TABLE_SHOPPING_CART_HEAD_";

    //  服务员操作桌台信息
    public final static String WAITER_TABLE_HEAD = "WAITER_TABLE_HEAD_";
    //  扫店码或者APP点餐的购物车head
    public final static String APP_DEP_SHOPPING_CART_HEAD = "APP_DEP_SHOPPING_CART_HEAD_";

    //  购物车内使用的菜品列表的hashkey
    public final static String APP_SHOPPING_CART_DISH_MAP_HASHKEY = "APP_SHOPPING_CART_DISH_MAP_HASHKEY";
    //  购物车内参与点餐的消费者信息列表的hashkey
    public final static String APP_SHOPPING_CART_CONSUMMER_MAP_HASHKEY = "APP_SHOPPING_CART_CONSUMMER_MAP_HASHKEY";
    //  购物车的信息记录hashkey
    public final static String APP_SHOPPING_CART_INFO_HASHKEY = "APP_SHOPPING_CART_INFO_HASHKEY";
    //  返回给页面的购物车的key
    public static final String SHOPPING_CART_INFO_VO_KEY = "shopping_cart_info_vo";
    public static final String SHOPPING_CART_DISHES_VO_KEY = "shopping_cart_dishes_vo";
    public static final String SHOPPING_CART_CONSUMER_VO_KEY = "shopping_cart_consumer_vo";

    //服务员扫码绑定锁头标记
    public static final String WAITER_BIND_QRCODE = "WAITER_BIND_QRCODE";

    //服务员扫码绑定锁头标记
    public static final  String WAITER_CONCENT_TABLE = "WAITER_CONCENT_TABLE";

    //服务员开台锁头标记
    public static final String WAITER_SET_TABLE = "WAITER_SET_TABLE";

    //服务员清台锁头标记
    public static final String WAITER_CLEAN_TABLE = "WAITER_CLEAN_TABLE";

    //    APP消费者订单redis锁标头
    public static final String ORDER_REDIS_LOCK = "ORDER_REDIS_LOCK_";

    // 扫描店码桌码到redis生成的订单信息
    public static final String SCAN_TABLE_ORDER_HEAD = "APP_SCAN_TABLE_ORDER_HEAD_";

    //收款二维码头规则
    public static final String RECIEV_CODE = "RECIVE_CODE";

    //支付二维码头规则
    public static final String PAY_CODE = "PAY_CODE";

    //二次输入金额缓冲头规则
    public static final String PAY_INPUT_CODE = "PAY_INPUT_CODE";

    //服务员消息中心
    public static final String WAITER_CENTER_MSG = "WAITER_CENTER_MSG_";

    //服务员首页桌台信息标头
    public static final String WAITER_TABLES_INFO_HEAD = "WAITER_TABLES_INFO_HEAD_";

    //服务员下的订单的标头
    public static final String WAITER_ORDER_HEAD = "WAITER_ORDER_HEAD_";


    //店铺统计付款方式统计
    public static final String DEP_COUNT_PAYWAY = "DEP_COUNT_PAYWAY_";

    //店铺统计就餐状态统计
    public static final String DEP_COUNT_SOURCE = "DEP_COUNT_SOURCE_";

    //店铺订单来源统计
    public static final String ORDER_COUNT_SOURCE = "ORDER_COUNT_SOURCE_";

    //店铺统计点餐方式统计
    public static final String DEP_COUNT_SINCE = "DEP_COUNT_SINCE_";

    //门店菜品销售统计
    public static final String DISH_SALES_COUNT = "DISH_SALES_COUNT_";

    //门店配置头
    public static final String DEPARTMENT_CONFIG = "DEPARTMENT_CONFIG";

    //短信验证码头
    public static final String SMS_VER_CODE = "SMS_VER_CODE";

}
