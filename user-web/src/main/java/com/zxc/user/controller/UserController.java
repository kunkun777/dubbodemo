package com.zxc.user.controller;

import com.zxc.user.service.UserService;
import com.zxc.user.service.UserService1;
import com.zxc.user.service.UserService2;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UserController {

    @Reference(version = "1.0.0",url = "dubbo://127.0.0.1:12345",retries = 5,cluster = "failover")
    private UserService userService;
    @Reference(version = "1.0.0",url = "dubbo://127.0.0.1:12346")
    private UserService1 userService1;
    @Reference(version = "1.0.0",url = "dubbo://127.0.0.1:12347")
    private UserService2 userService2;

    @RequestMapping("/hello")
    public String sayHello(){
        return userService.sayHello();
    }

    @RequestMapping("/hi")
    public String sayHi() throws IOException {
        return userService1.sayHi();
    }

    @RequestMapping("/getAddress")
    public String getAddress(){
        return userService.getUserAddressList().toString();
    }

    @RequestMapping("/inComplied")
    public String inComplied(){
        return userService2.incompled();
    }
}
