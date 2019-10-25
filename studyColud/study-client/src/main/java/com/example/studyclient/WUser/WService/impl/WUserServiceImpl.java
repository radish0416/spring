package com.example.studyclient.WUser.WService.impl;



import com.example.studyapi.bean.Login;
import com.example.studyapi.bean.Result;
import com.example.studyapi.util.HttpEntityUtils;
import com.example.studyapi.util.MicroServiceList;
import com.example.studyclient.WUser.WService.WUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WUserServiceImpl implements WUserService {

    private static String REQ_PREFIX = "/app/dp/user";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Result login(Login login) {
        HttpEntity<Object> data = HttpEntityUtils.packageSendJSON(login);
        Result  result = restTemplate.postForObject(MicroServiceList.STUDY_SERVER + REQ_PREFIX+"/login", data, Result.class);
        return result;
    }
}
