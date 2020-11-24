package com.zxc.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zxc.user.entity.userAddress;
import com.zxc.user.service.UserService;

import java.util.ArrayList;
import java.util.List;


@Service(version = "1.0.0",stub = "com.zxc.stub.serviceStub")
public class UserServiceImpl implements UserService {

    public List<userAddress> getUserAddressList() {
        List<userAddress> re=new ArrayList<userAddress>();
        re.add(new userAddress(1,"北京","1","ww","17816073451"));
        re.add(new userAddress(2,"北京","2","kk","17816068476"));
        return re;
    }

    public String sayHello() {
        return "hello!";
    }


}
