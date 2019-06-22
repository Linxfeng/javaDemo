package com.lint.rpc.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RMI对外服务接口
 *
 * @author lintao
 * @date 2019/6/22
 */
public interface HelloService extends Remote {

    String sayHello(String someOne) throws RemoteException;
}
