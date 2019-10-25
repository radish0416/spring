package com.example.studyapi.bean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "最基础的请求")
public class BaseToken {

    @ApiModelProperty(value = "token")
    private String token;
}
