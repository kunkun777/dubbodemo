package com.zxc.user.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class userAddress implements Serializable {

    int num;
    String address;
    String userNum;
    String username;
    String phone;

    public userAddress(int num, String address, String userNum, String username, String phone) {
        this.num = num;
        this.address = address;
        this.userNum = userNum;
        this.username = username;
        this.phone = phone;
    }
}
