package com.zxc.service.impl;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.zxc.user.service.UserService1;
import com.zxc.user.service.UserService2;

@Service(version = "1.0.0",group = "group1")
public class UserService1Impl implements UserService1 {

//    @Reference(version = "1.0.0")
//    UserService2 userService2;

    public String sayHi() {
//        return "hi!"+"此处还调用了服务2："+userService2.incompled();
        return "hi!这是服务1的分组1";
    }

}
