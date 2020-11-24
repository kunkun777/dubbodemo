package com.zxc.user.service;

import com.zxc.user.entity.userAddress;

import java.util.List;

public interface UserService {
    List<userAddress> getUserAddressList();
    String sayHello();
}
