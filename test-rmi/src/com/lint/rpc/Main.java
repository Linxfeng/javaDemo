package com.lint.rpc;

import com.lint.rpc.rmi.client.ClientMain;
import com.lint.rpc.rmi.server.ServerMain;

public class Main {

    public static void main(String[] args) throws Exception {
        /** 启动RMI服务端 */
        ServerMain.main(args);
        /** 启动RMI客户端 */
        ClientMain.main(args);
    }
}
