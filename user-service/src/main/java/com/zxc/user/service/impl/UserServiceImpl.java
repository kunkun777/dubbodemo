package com.zxc.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zxc.user.service.UserService;


@Service(version = "1.0.0")
public class UserServiceImpl implements UserService {

    public String sayHello() {
        return "hello!";
    }

}
