package com.lcl.Socket.bioSocket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: SocketServer
 * @date 2019/10/9 5:07 下午
 */
public class SocketServer {
    public static void main(String[] args) throws IOException {
        //指定监听端口
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        //等待连接到来
        System.out.println("waiting for connect");
        Socket socket = serverSocket.accept();
        //使用io流读取客户端发送的消息
        InputStream in = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while ((len = in.read(bytes)) != -1) {
            stringBuilder.append(new String(bytes, 0, len, "UTF-8"));
        }
        System.out.println("the message is");
        System.out.println(stringBuilder);
        in.close();
        socket.close();
        serverSocket.close();
    }

}
