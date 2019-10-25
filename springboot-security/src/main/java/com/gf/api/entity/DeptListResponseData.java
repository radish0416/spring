package com.gf.api.entity;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IDEA
 * author:WuHeng
 * Date:2019/5/31 0031
 * Time:下午 3:29
 */
@ApiModel(value = "门店列表返回")
@Data
public class DeptListResponseData {
    @ApiModelProperty(value = "企业id")
    private int faher_ID;
    @ApiModelProperty(value = "门店id")
    private int dep_ID;
    @ApiModelProperty(value = "门店logo")
    private String dep_logo;

    @ApiModelProperty(value = "门店名称")
    private String dep_comp_name;

    @ApiModelProperty(value = "门店简称")
    private String dep_comp_short_name;

    @ApiModelProperty(value = "门店电话")
    private String dep_phone;

    @ApiModelProperty(value = "门店地址")
    private String dep_address;

    @ApiModelProperty(value = "餐饮类型")
    private String food_type;

    @ApiModelProperty(value = "店长名")
    private String dep_shopowner;

    @ApiModelProperty(value = "店长手机")
    private String dep_mobile;

    @ApiModelProperty(value = "门店状态")
    private String dep_sts;

    @ApiModelProperty(value = "经营开始时间")
    private String open_time_start;

    @ApiModelProperty(value = "经营结束时间")
    private String open_time_end;

    @ApiModelProperty(value = "门店账户id")
    private String account_id;

    @ApiModelProperty(value = "门店二维码")
    private String qr_code;

    @ApiModelProperty(value = "门店TAG，特色")
    private String dep_tag;

    @ApiModelProperty(value = "门店引导页状态,1开,0关,默认开着")
    private String guide_status;

}

