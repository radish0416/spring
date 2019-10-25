package com.example.studyserver.user.controller;


import com.example.studyapi.bean.Login;
import com.example.studyapi.bean.Result;
import com.example.studyserver.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/dp/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录操作
     * @param login
     * @return
     */
    @PostMapping(value = "/login")
    public Result sendSMSReg(@RequestBody Login login) {
        return userService.login(login);
    }
}
