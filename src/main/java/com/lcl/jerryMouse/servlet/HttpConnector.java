package com.lcl.jerryMouse.servlet;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: HttpConnector
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2025/3/21 01:32
 */
public class HttpConnector implements HttpHandler, AutoCloseable {

    final HttpServer server;

    public HttpConnector() throws IOException {
        this.server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
        this.server.createContext("/", this);
        this.server.start();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        HttpExchangeAdapter httpExchangeAdapter = new HttpExchangeAdapter(httpExchange);
        HttpServletRequestImpl request = new HttpServletRequestImpl(httpExchangeAdapter);
        HttpServletResponseImpl response = new HttpServletResponseImpl(httpExchangeAdapter);

        process(request, response);
    }

    public void process(HttpServletRequest request, HttpServletResponse response) {
        // TODO do some print

    }

    @Override
    public void close() throws Exception {
        this.server.stop(3);
    }
}
