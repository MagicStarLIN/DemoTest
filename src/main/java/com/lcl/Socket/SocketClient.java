package com.lcl.Socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: SocketClient
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/10/9 5:08 下午
 */
public class SocketClient {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        //声明要连接的ip地址和端口
        String host = "127.0.0.1";
        int port = 8080;
        //建立连接
        Socket socket = new Socket(host, port);
        OutputStream outputStream = socket.getOutputStream();
        System.out.println("please input the message");
        String message = sc.next();
        outputStream.write(message.getBytes("UTF-8"));
        outputStream.close();
        socket.close();
    }
}
