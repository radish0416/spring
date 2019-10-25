package com.gf.company.security.mapper;


import com.gf.api.entity.RoleResourceInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Li Xianjiang
 * @version 1.0
 * @Date Created in 2019/7/26
 */
@Mapper
@Component
public interface UserRoleResourceDao {

    /**
     * 根据res code获取资源角色信息
     * @params [resCode]
     * @return com.shule.springcloud.entities.security.RoleResourceInfo
     * @version 1.0
     */
    @Select("select * from t_role_resource where res_code = #{resCode} and app_type = #{appType}")
    RoleResourceInfo getRoleResourceInfo(String resCode, String appType);

    @Delete("delete from t_role_resource where app_type = #{appType}")
    int removeAllResource(String appType);

    @Insert("insert into t_role_resource (res_code,\n" +
            "res_type,\n" +
            "res_name,\n" +
            "res_value,\n" +
            "auth_code,\n" +
            "auth_name,\n" +
            "role_code,\n" +
            "role_name,\n" +
            "app_type,\n" +
            "create_time\n) values(#{res_code},\n" +
            "#{res_type},\n" +
            "#{res_name},\n" +
            "#{res_value},\n" +
            "#{auth_code},\n" +
            "#{auth_name},\n" +
            "#{role_code},\n" +
            "#{role_name},\n" +
            "#{app_type},\n" +
            "now()\n)")
    int insertRoleResource(RoleResourceInfo info);

    @Update("update t_role_resource set res_type = #{res_type},res_name = #{res_name},res_value = #{res_value}" +
            ",role_code = #{role_code},auth_code = #{auth_code},auth_name = #{auth_name}" +
            ",role_name = #{role_name} where res_code = #{res_code}")
    void updateRoleResource(RoleResourceInfo info);

    /**
     * 根据应用名称查询应用所有的角色列表
     *
     * @return java.util.List<java.lang.String>
     * @params []
     * @version 1.0
     */
    @Select("select distinct role_code from t_role_resource where app_type = #{appType}")
    List<String> getAllRoles(String appType);

    /**
     * 根据应用名称查询应用所有的角色资源列表
     *
     * @return java.util.List<com.shule.springcloud.entities.security.RoleResourceInfo>
     * @params [appType]
     * @version 1.0
     */
    @Select("select * from t_role_resource where app_type = #{appType} limit 200")
    List<RoleResourceInfo> getAllResource(String appType);

    /**
     * 根据权限code获取资源列表
     * @params [appType, roleCode]
     * @return java.util.List<com.shule.springcloud.entities.security.RoleResourceInfo>
     * @version 1.0
     */
    @Select("<script> select * from t_role_resource where app_type = #{appType} and auth_code in " +
            "<foreach collection=\"auths\" item=\"auth\" open=\"(\" close=\")\" separator=\",\">\n" +
            "  #{auth}\n" +
            "</foreach></script> ")
    List<RoleResourceInfo> getResourcesByAuthCode(@Param("appType") String appType, @Param("auths") List<String> auths);

    /**
     * 根据用户ID获取角色列表
     * @params [userID]
     * @return java.util.List<java.lang.String>
     * @version 1.0
     */
    @Select("select distinct role_code from t_role_dep_user where user_id = #{userID} and rec_sts = '0' ")
    List<String> getUserRoles(int userID);


    /**
     * 根据用户ID获取权限列表
     * @params [userID]
     * @return java.util.List<java.lang.String>
     * @version 1.0
     */
    @Select("select auth_code from t_role_dep_user where user_id = #{userID} and rec_sts = '0' ")
    List<String> getUserAuths(int userID);

}
