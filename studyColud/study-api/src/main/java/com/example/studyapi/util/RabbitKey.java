package com.example.studyapi.util;

public class RabbitKey {

    public static final  String Dep_count_MSG = "Dep_count_MSG";
    public static final String DISH_TYPE_QUEUE = "dish_type_queue11";
    public static final String TABLE_QUEUE = "table_queue";
    public static final String MAIN_PAGE_QUEUE = "main_page_queue";
    public static final String DEP_INFO_QUEUE = "dep_info_queue";
    public static final String DEP_INFO_UPDATE_QUEUE = "dep_info_update_queue";
    public static final String APP_QUEUE = "app_code";
    public static final String APP_PUSH_IOS_MESSAGE_ORDERING_QUEUE = "app_push_IOS_message_ordering_queue";
    public static final String APP_PUSH_ANDROID_MESSAGE_ORDERING_QUEUE = "app_push_ANDROID_message_ordering_queue";

    public static final String IMAGE_UPLOAD_COMPRESSION = "image_upload_compression";

    //  企业用户短信redis queue
    public static final String COMPANY_USER_SMS_QUEUE = "COMPANY_USER_SMS_QUEUE";
    //    向手机发送验证码队列
    public static final String PHONE_CODE_SMS_QUEUE = "PHONE_CODE_SMS_QUEUE";
    //商户后台用户发送验证码
    public static final String MIAOSHA_QUEUE = "MIAOSHA_QUEUE";
    public static final String ORDER_QUEUE = "neworder";

    //    更新菜品分类
    public static final String MYBATIS_QUEUE = "mybatis";
    //    设置套餐下架
    public static final String MYBATIS_QUEUE1 = "mybatis1";
    //    下单后设置redis与MySQL内的菜品库存
    public static final String DISH_INVENTORY_CHANGE = "DISH_INVENTORY_CHANGE";

    //更新桌台区域
    public static final String UPDATE_TABLEPOS = "update_table_pos";

    //更新职级
    public static final String UPDATE_WORKER_CLASS = "update_work_class";

    public static final String DETELE_DEPT_CONFIG  = "delete_dept_config";

    //订单完成后将菜品信息统计
    public static final String Dish_count_MSG = "Dish_count_MSG";

    //对账中心记录
    public static final String Check_center = "Check_center";

    //处理退菜，赠菜
    public static final String RETURN_GIVE_DISH = "RETURN_GIVE_DISH";

    //处理用户点过的菜
    public static final String USER_ORDER_DISH = "USER_ORDER_DISH";

    //处理退款
    public static final String RETURN_ACCOUNT = "RETURN_ACCOUNT";

    public static final String SERVICE_BELL = "service_bell";


    //记录钱包交易流水
    public static final String WALLET_TRANSFER = "WALLET_TRANSFER";

    //发短信APP下载提示
    public static final  String SEND_SMS_DOWNLOAD = "SEND_SMS_DOWNLOAD";

    //扫码支付消息推送
    public static final  String WALLET_PUSH_ANDROID = "WALLET_PUSH_ANDROID";
    public static final  String WALLET_PUSH_IOS = "WALLET_PUSH_IOS";

    //发送验证码全局队列
    public static final  String SEND_AWS_SMS = "SEND_AWS_SMS";

}
