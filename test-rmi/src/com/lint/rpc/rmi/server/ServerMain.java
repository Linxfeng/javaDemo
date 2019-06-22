package com.lint.rpc.rmi.server;

import com.lint.rpc.rmi.CustomerSocketFactory;
import com.lint.rpc.rmi.HelloService;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RMISocketFactory;

/**
 * RMI服务端启动类
 *
 * @author lintao
 * @date 2019/6/22
 */
public class ServerMain {

    public static void main(String[] args) throws Exception {
        //注册服务
        LocateRegistry.createRegistry(8001);
        //指定通讯端口，防止被防火墙拦截
        RMISocketFactory.setSocketFactory(new CustomerSocketFactory());
        HelloService helloService = new HelloServiceImpl();
        Naming.bind("rmi://localhost:8001/helloService", helloService);
        System.out.println("ServerMain provider RMI server started!");
    }
}
