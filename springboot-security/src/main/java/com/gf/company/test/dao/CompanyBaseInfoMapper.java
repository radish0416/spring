package com.gf.company.test.dao;



import com.gf.api.entity.CompanyBaseInfo;
import com.gf.api.entity.CompanyInfo;
import com.gf.api.entity.RoleDepUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface CompanyBaseInfoMapper {
    /**
     * 企业用户添加
     * author:wuheng
     */
    //通过手机号和密码添加企业员工，添加t_admin_user表
//    @Insert("insert into t_admin_user(mobile_inf,pwd,last_login_ip,flag,dept_id,class_id,class_name,last_login_time) " +
//            "values(#{mobile_inf},#{pwd},#{last_login_ip},#{flag},#{dept_id},#{class_id},#{class_name},now())")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    int insert(CompanyBaseInfo record);


    /**
     * 企业用户注册表
     * @param companyInfo
     * @return
     */
    @Insert("insert into t_company_info(id,mobile_inf,pwd,last_login_ip,logo,employ_name,last_login_time) " +
            "values(next value for MYCATSEQ_DEPCOMPINFO,#{mobile_inf},#{pwd},#{last_login_ip}" +
            ",substring_index(#{logo},'${@com.shule.springcloud.entities.StaticFilePaths@IMAGES_ACCESS_PATH}',-1)" +
            ",#{employ_name},now())")
    @Options( useGeneratedKeys = true, keyProperty = "id")
    int insertCompany(CompanyInfo companyInfo);


    @Insert("insert into t_role_dep_user(id,user_id,dep_id,company_id,auth_code) " +
            "values(next value for MYCATSEQ_DEPCOMPINFO,#{user_id},#{dep_id},#{company_id},#{auth_code})")
    @Options( useGeneratedKeys = true, keyProperty = "id")
    int insertCompanyRoleUser(RoleDepUser roleDepUser);


    //查询权限表
    @Select("select * from t_role_dep_user where user_id=#{user_id} and rec_sts='0'")
    RoleDepUser selectRoleDepUserById(int user_id);

    //更新员工权限表
    @Update("update t_role_dep_user set auth_code = #{auth_code},update_time=now() where user_id=#{user_id}")
    int updateWorkerRoleUser(RoleDepUser roleDepUser);



    //通过手机号查找员工表
    @Select("select id\n" +
            ",company_id\n" +
            ",dept_id\n" +
            ",mobile_inf\n" +
            ",pwd\n" +
            ",employ_name\n" +
            ",email\n" +
            ",auth\n" +
            ",rec_sts\n" +
            ",status\n" +
            ",last_login_ip\n" +
            ",cre_time\n" +
            ",update_time\n" +
            ",last_login_time\n" +
            ",sex\n" +
            ",concat('${@com.shule.springcloud.entities.StaticFilePaths@IMAGES_SERVER_PATH}',logo) as  logo\n" +
            ",id_card\n" +
            ",class_id\n" +
            ",class_name\n" +
            ",account_id\n" +
            ",ieme from t_admin_user where mobile_inf=#{mobileInf} and rec_sts!='1'")
    CompanyBaseInfo findCompanyByPhone(String mobileInf);

    //通过手机号查找企业表
    @Select("select id\n" +
            ",mobile_inf\n" +
            ",pwd\n" +
            ",employ_name\n" +
            ",cre_time\n" +
            ",update_time\n" +
            ",rec_sts\n" +
            ",status\n" +
            ",last_login_ip\n" +
            ",last_login_time\n" +
            ",class_id\n" +
            ",class_name\n" +
            ",concat('${@com.shule.springcloud.entities.StaticFilePaths@IMAGES_SERVER_PATH}',logo) as  logo\n" +
            ",account_id\n from t_company_info where mobile_inf=#{mobileInf} and rec_sts!='1'")
    CompanyInfo findBigCompanyByPhone(String mobileInf);

    //通过id查找企业表
    @Select("select id\n" +
            ",mobile_inf\n" +
            ",pwd\n" +
            ",employ_name\n" +
            ",cre_time\n" +
            ",update_time\n" +
            ",rec_sts\n" +
            ",status\n" +
            ",last_login_ip\n" +
            ",last_login_time\n" +
            ",class_id\n" +
            ",class_name\n" +
            ",concat('${@com.shule.springcloud.entities.StaticFilePaths@IMAGES_SERVER_PATH}',logo) as  logo\n" +
            ",account_id\n from t_company_info where id=#{id} and rec_sts!='1'")
    CompanyInfo findCompanyInfoById(int id);

    //通过id查找所有员工信息
    @Select("select id\n" +
            ",company_id\n" +
            ",dept_id\n" +
            ",mobile_inf\n" +
            ",pwd\n" +
            ",employ_name\n" +
            ",email\n" +
            ",auth\n" +
            ",rec_sts\n" +
            ",status\n" +
            ",last_login_ip\n" +
            ",cre_time\n" +
            ",update_time\n" +
            ",last_login_time\n" +
            ",sex\n" +
            ",concat('${@com.shule.springcloud.entities.StaticFilePaths@IMAGES_SERVER_PATH}',logo) as  logo\n" +
            ",id_card\n" +
            ",class_id\n" +
            ",class_name\n" +
            ",account_id\n" +
            ",ieme\n from t_admin_user where id=#{id} and rec_sts!='1'")
    CompanyBaseInfo findAllCompanyById(int id);

}
