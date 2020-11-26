package com.zxc.stub;

import com.zxc.user.entity.userAddress;
import com.zxc.user.service.UserService1;

import java.util.ArrayList;
import java.util.List;

public class serviceStub implements UserService1 {

    private UserService1 userService;

    public serviceStub(UserService1 userService){
        this.userService=userService;
    }

    @Override
    public String sayHi() {
        return null;
    }
}
