package com.gf.api.entity;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 封装各种不相关的请求参数
 * @author Liu Jinglei
 * @Date Created in 2019/5/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BaseRequestTokenBean<T> implements Serializable {

    @ApiModelProperty("特指企业ID")
    private int company_id;
    private int param;
    private int param1;
    private int param2;
    private int param3;
    private int dep_id;
    private String token;

    private BigDecimal float_param;

    private String str_param;
    private String str_param1;
    //  页数
    private int page_num;
    //  每页记录数
    private int page_size;

    //    @ApiModelProperty(value = "传入list类型的参数",notes = "")
    private List list;

    private Map map;

    private T data;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
