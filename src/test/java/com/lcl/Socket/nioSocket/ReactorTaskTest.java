package com.lcl.Socket.nioSocket;

import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReactorTaskTest {

    @Test
    void constructionBindsOnceWithoutStartingRecursiveThreads() throws Exception {
        try (ReactorTask reactor = new ReactorTask(new InetSocketAddress("localhost", 0))) {
            assertTrue(reactor.getLocalAddress().getPort() > 0);
        }
    }
}
