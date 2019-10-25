package com.gf.api.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/**
 * @author Li Xianjiang
 * @version 1.0
 * @Date Created in 2019/7/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "角色资源权限信息")
public class RoleResourceInfo {

    private String     res_code;
    private String     res_type;
    private String     res_name;
    private String     res_value;
    private String     auth_code;
    private String     auth_name;
    private String     role_code;
    private String     role_name;
    private String     app_type;
    private Timestamp  create_time;

    @Override
    public String toString() {
        return "RoleResourceInfo{" +
                "res_code='" + res_code + '\'' +
                ", res_type='" + res_type + '\'' +
                ", res_name='" + res_name + '\'' +
                ", res_value='" + res_value + '\'' +
                ", auth_code='" + auth_code + '\'' +
                ", auth_name='" + auth_name + '\'' +
                ", role_code='" + role_code + '\'' +
                ", role_name='" + role_name + '\'' +
                ", app_type='" + app_type + '\'' +
                ", create_time=" + create_time +
                '}';
    }
}

