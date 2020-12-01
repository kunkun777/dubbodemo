package com.zxc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zxc.user.service.UserService2;

@Service(version = "1.0.0")
public class UserService2Impl implements UserService2 {

    @Override
    public String incompled(String id) {
            return "这是未完成的服务";
    }
}
