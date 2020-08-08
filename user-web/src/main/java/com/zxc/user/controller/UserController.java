package com.zxc.user.controller;

import com.zxc.user.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Reference(version = "1.0.0",url = "dubbo://127.0.0.1:12345")
    private UserService userService;

    @RequestMapping("/hello")
    public String sayHello(){
        return userService.sayHello();
    }
}
