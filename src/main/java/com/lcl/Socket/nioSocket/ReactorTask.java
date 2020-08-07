package com.lcl.Socket.nioSocket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class ReactorTask implements Runnable {

    ServerSocketChannel acceptorSvr;
    int port = 8080;

    public ReactorTask() {
        try {
            acceptorSvr = ServerSocketChannel.open();
            acceptorSvr.socket().bind(new InetSocketAddress(InetAddress.getByName("IP"), port));
            acceptorSvr.configureBlocking(false);

            Selector selector = Selector.open();
            new Thread(new ReactorTask()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }
}
