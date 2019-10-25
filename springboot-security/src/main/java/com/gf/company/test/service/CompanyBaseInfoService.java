package com.gf.company.test.service;

import com.gf.api.entity.ComResponseData;
import com.gf.api.entity.DeptListResponseData;
import com.gf.api.entity.ShuleUserDetails;

import java.util.List;

public interface CompanyBaseInfoService {
    /**
     * 根据用户信息获取企业信息
     * @param userDetails
     * @return
     */
    ComResponseData<List<DeptListResponseData>> getCompanyByUser(ShuleUserDetails userDetails);
}
