package com.example.studyclient.WUser.WService;

import com.example.studyapi.bean.Login;
import com.example.studyapi.bean.Result;

public interface WUserService {

    //登录操作
    Result login(Login login);
}
