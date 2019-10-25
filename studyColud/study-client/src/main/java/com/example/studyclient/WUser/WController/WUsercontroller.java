package com.example.studyclient.WUser.WController;

import com.example.studyapi.bean.Login;
import com.example.studyapi.bean.Result;
import com.example.studyclient.WUser.WService.WUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags = "登录")
@RequestMapping("/apis/dp")
public class WUsercontroller {

    @Autowired
    private WUserService userService;
    /**
     * 登录
     *
     * @param login
     * @return
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result sendSms(@RequestBody Login login) {
        return userService.login(login);
    }

}
