package com.zxc.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.zxc.user.service.UserService1;

@Service(version = "1.0.0")
public class UserService1Impl implements UserService1 {
    public String sayHi() {
        return "hi!";
    }
}
