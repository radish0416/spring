package com.gf.company.test.service.impl;

import com.gf.api.entity.ComResponseData;
import com.gf.api.entity.DeptListResponseData;
import com.gf.api.entity.ShuleUserDetails;
import com.gf.company.test.service.CompanyBaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CompanyBaseInfoImpl implements CompanyBaseInfoService {
    @Override
    public ComResponseData<List<DeptListResponseData>> getCompanyByUser(ShuleUserDetails userDetails) {
        return null;
    }
}
