package com.zxc.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zxc.user.entity.userAddress;
import com.zxc.user.service.UserService;
import org.apache.dubbo.registry.client.metadata.StandardMetadataServiceURLBuilder;
import sun.applet.Main;

import java.util.ArrayList;
import java.util.List;


//@Service(version = "1.0.0",stub = "com.zxc.stub.serviceStub")
@Service(version = "1.0.0",cluster = "failover",retries = 5)
public class UserServiceImpl implements UserService {

    int num = 0;
    @HystrixCommand(fallbackMethod = "ifError")
    @Override
    public List<userAddress> getUserAddressList() {
        System.out.println("调用的第"+ num++ +"次！");
        List<userAddress> re=new ArrayList<userAddress>();
        re.add(new userAddress(1,"北京","1","ww","17816073451"));
        re.add(new userAddress(2,"北京","2","kk","17816068476"));
//        if (Math.random()>0.5)
            throw new RuntimeException();
//        return re;
    }

    public List<userAddress> ifError(){
        return new ArrayList<userAddress>();
    }

    @Override
    public String sayHello() {
        return "hello!";
    }


}
