package com.gf.api.entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IDEA
 * author:WuHeng
 * Date:2019/6/3 0003
 * Time:下午 2:27
 */
@ApiModel(value = "分页列表返回基类")
@Data
public class PageListResponse<T> {
    @ApiModelProperty(value = "总页数")
    private int all_pages;

    @ApiModelProperty(value = "总条数")
    private int all_rows;

    @ApiModelProperty(value = "所有桌台总数")
    private int all_count;

//    @ApiModelProperty(value = "手机号")
//    private String employ_name;

//    @ApiModelProperty(value = "头像")
//    private String logo;

    @ApiModelProperty(value = "data")
    private T data;
}
