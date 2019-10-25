package com.gf.company.test.controller;

import com.gf.api.AuthEnums;
import com.gf.api.RoleEnums;
import com.gf.api.entity.Result;
import com.gf.company.security.compont.AuthResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IDEA
 * author:WuHeng
 * Date:2019/5/20 0020
 * Time:上午 11:41
 * 商户员工controller
 */
@Slf4j
@RestController
@RequestMapping("/apis/company")
public class CompanyBaseController {

    //authCode代表所需要的权限，roleCode代表的是角色，roleName和resName代表接口描述也就是资源描述，resCode代表的是唯一标识，这个不能重复
    @AuthResource(resCode = "COMPANY_DEP_COUNT_EATTYPE", resName = "订单就餐状态统计"
            ,authCode = AuthEnums.AUTH_COMPANY_BUSINESS
            ,roleCode = {RoleEnums.ROLE_DEPART_MANAGER},roleName = "订单就餐状态统计")
    public Result orderCount(){
        return null;
    }
}
