package com.zxc.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zxc.user.service.UserService1;
import com.zxc.user.service.UserService2;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

//    @Reference(version = "1.0.0",registry = "false",url = "dubbo://127.0.0.1:12345")
    @Reference(version = "1.0.0",group = "group1")
    private UserService1 userService1;
    @Reference(version = "1.0.0",group = "*")
    private UserService1 userService1_2;
    @Reference(version = "1.0.0",generic=true,interfaceName = "com.zxc.user.service.UserService2")
    private UserService2 userService2;


    @RequestMapping("/hi")
    public String sayHello(){
        return userService1.sayHi();
    }

    @RequestMapping("/hi2")
    public String sayHello2(){
        return userService1_2.sayHi();
    }

    @RequestMapping("/incompled")
    public String sayHi(){
        // 引用远程服务
// 该实例很重量，里面封装了所有与注册中心及服务提供方连接，请缓存
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
// 弱类型接口名
        reference.setInterface("com.zxc.user.service.UserService2");
        reference.setVersion("1.0.0");
// 声明为泛化接口
        reference.setGeneric(true);

// 用org.apache.dubbo.rpc.service.GenericService可以替代所有接口引用
        GenericService genericService = reference.get();

// 基本类型以及Date,List,Map等不需要转换，直接调用
        Object result = genericService.$invoke("incompled", new String[] {"java.lang.String"}, new Object[] {"world"});
        System.out.println(result);
        return "userService2.incompled()";
    }

}
