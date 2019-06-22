package com.lint.rpc.rmi.server;

import com.lint.rpc.rmi.HelloService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 服务端接口实现
 *
 * @author lintao
 * @date 2019/6/22
 */
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    public HelloServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String someOne) throws RemoteException {
        return "Hello, " + someOne;
    }
}
