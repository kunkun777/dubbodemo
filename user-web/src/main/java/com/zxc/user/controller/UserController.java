package com.zxc.user.controller;

import com.zxc.user.service.UserService1;
import com.zxc.user.service.UserService2;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Reference(version = "1.0.0",url = "dubbo://127.0.0.1:12345")
    private UserService1 userService1;
    @Reference(version = "1.0.0",url = "dubbo://127.0.0.1:12346")
    private UserService2 userService2;


    @RequestMapping("/hi")
    public String sayHello(){
        return userService1.sayHi();
    }

    @RequestMapping("/incompled")
    public String sayHi(){
        return userService2.incompled();
    }

}
