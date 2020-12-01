package com.zxc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zxc.user.service.UserService1;


@Service(version = "1.0.0",group = "group2")
public class UserService2Impl implements UserService1 {

    @Override
    public String sayHi() {
        return "hi! 这是服务1的分组2";
    }
}
