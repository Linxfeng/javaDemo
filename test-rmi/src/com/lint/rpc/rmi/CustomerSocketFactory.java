package com.lint.rpc.rmi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

/**
 * 指定通讯端口，防止被防火墙拦截
 *
 * @author lintao
 * @date 2019/6/22
 */
public class CustomerSocketFactory extends RMISocketFactory {

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        return new Socket(host, port);
    }

    @Override
    public ServerSocket createServerSocket(int port) throws IOException {
        if (port == 0) port = 8002;
        System.out.println("RMI notify post: " + port);
        return new ServerSocket(port);
    }
}
