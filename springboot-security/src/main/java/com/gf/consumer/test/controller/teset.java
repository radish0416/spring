package com.gf.consumer.test.controller;

import com.gf.api.AuthEnums;
import com.gf.api.entity.Result;
import com.gf.consumer.security.annotation.NeedLogin;
import com.gf.consumer.security.annotation.WaiterAuthentication;
import org.springframework.web.bind.annotation.PostMapping;


public class teset {
    @NeedLogin
    @PostMapping("/getdepinfo")
    public Result getDepInfoByQRCode(){
        return null;
    }

    @WaiterAuthentication(authCode = AuthEnums.AUTH_WAITER_OPEN_ORDER)
    @PostMapping("/waitergetonedish")
    public Result waiterGetOneDishByDishID() {
        return null;
    }
}
