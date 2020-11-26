package com.zxc.stub;

import com.zxc.user.entity.userAddress;
import com.zxc.user.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class serviceStub implements UserService {

    UserService userService;

    public serviceStub(UserService userService){
        this.userService=userService;
    }

    public List<userAddress> getUserAddressList() {
        System.out.println("调用了本地的存根");
        ArrayList<userAddress> userAddresses = new ArrayList<userAddress>();
        userAddresses.add( new userAddress(1,"1","1","1","1"));
        return userService.getUserAddressList();
    }

    public String sayHello() {
        return "null";
    }
}
