package com.gf.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 用户权限表
 * Created with IDEA
 * author:nick.niu
 * Date:2019/5/27
 * Time:19:21
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class RoleDepUser implements Serializable {
    private int id;

    private int user_id;

    private int dep_id;

    private int company_id;

    private String auth_code;

}