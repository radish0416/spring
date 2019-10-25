package com.example.studyserver.user.service.Impl;

import com.example.studyapi.bean.Login;
import com.example.studyapi.bean.Result;
import com.example.studyserver.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    public Result login(Login login) {
        String success = "success";
        return Result.formatRet(success);
    }
}
