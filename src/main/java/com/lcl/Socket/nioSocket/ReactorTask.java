package com.lcl.Socket.nioSocket;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ReactorTask implements Runnable, AutoCloseable {

    private final ServerSocketChannel acceptorSvr;
    private final Selector selector;
    private final InetSocketAddress localAddress;

    public ReactorTask(InetSocketAddress address) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        Selector openedSelector = null;
        try {
            serverChannel.bind(address);
            serverChannel.configureBlocking(false);
            openedSelector = Selector.open();
            serverChannel.register(openedSelector, SelectionKey.OP_ACCEPT);
            localAddress = (InetSocketAddress) serverChannel.getLocalAddress();
        } catch (IOException | RuntimeException failure) {
            closeAfterFailedConstruction(openedSelector, serverChannel, failure);
            throw failure;
        }
        acceptorSvr = serverChannel;
        selector = openedSelector;
    }

    public InetSocketAddress getLocalAddress() {
        return localAddress;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted() && selector.isOpen()) {
                selector.select();
                Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
                while (selectedKeys.hasNext()) {
                    SelectionKey key = selectedKeys.next();
                    selectedKeys.remove();
                    if (key.isAcceptable()) {
                        SocketChannel channel = acceptorSvr.accept();
                        if (channel != null) {
                            channel.close();
                        }
                    }
                }
            }
        } catch (ClosedSelectorException ignored) {
            // close() wakes and terminates the selector loop.
        } catch (CancelledKeyException failure) {
            if (selector.isOpen()) {
                throw failure;
            }
        } catch (IOException failure) {
            if (selector.isOpen()) {
                throw new UncheckedIOException(failure);
            }
        }
    }

    @Override
    public void close() throws IOException {
        IOException failure = null;
        try {
            selector.close();
        } catch (IOException closeFailure) {
            failure = closeFailure;
        }
        try {
            acceptorSvr.close();
        } catch (IOException closeFailure) {
            if (failure == null) {
                failure = closeFailure;
            } else {
                failure.addSuppressed(closeFailure);
            }
        }
        if (failure != null) {
            throw failure;
        }
    }

    private static void closeAfterFailedConstruction(
            Selector selector, ServerSocketChannel serverChannel, Throwable failure) {
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException closeFailure) {
                failure.addSuppressed(closeFailure);
            }
        }
        try {
            serverChannel.close();
        } catch (IOException closeFailure) {
            failure.addSuppressed(closeFailure);
        }
    }
}
