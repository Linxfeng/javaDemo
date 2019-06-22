package com.lint.rpc.rmi.client;

import com.lint.rpc.rmi.HelloService;

import java.rmi.Naming;

/**
 * RMI服务 客户端启动类
 *
 * @author lintao
 * @date 2019/6/22
 */
public class ClientMain {

    public static void main(String[] args) throws Exception {
        //服务引入
        HelloService helloService = (HelloService) Naming.lookup("rmi://localhost:8001/helloService");
        //调用远程方法
        System.out.println("RMI服务端返回的结果是：" + helloService.sayHello("World!"));
    }
}
