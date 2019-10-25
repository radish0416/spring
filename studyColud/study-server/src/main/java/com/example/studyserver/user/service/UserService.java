package com.example.studyserver.user.service;

import com.example.studyapi.bean.Login;
import com.example.studyapi.bean.Result;

public interface UserService {

    //登录操作
    Result login(Login login);
}
