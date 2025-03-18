package com.lcl.jerryMouse.http;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.http.client.utils.DateUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: SimpleHttpServer
 * @date 2025/3/18 01:58
 */
public class SimpleHttpServer implements HttpHandler, AutoCloseable {

    final HttpServer server;

    final String host;

    final int port;

    public SimpleHttpServer(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        this.server = HttpServer.create();
        this.server.bind(new java.net.InetSocketAddress(host, port), 0);
        this.server.createContext("/", this);
//        this.server.setExecutor(null);
        this.server.start();
    }


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestMethod = httpExchange.getRequestMethod();
        URI requestURI = httpExchange.getRequestURI();
        String path = requestURI.getPath();
        String host = requestURI.getHost();
        String port = String.valueOf(requestURI.getPort());
        String query = requestURI.getQuery();
        System.out.println("requestMethod: " + requestMethod);

        // print all
        System.out.println("requestURI: " + requestURI + "path: " + path + "host: " + host + "port: " + port
                + "query: " + query + "requestMethod: " + requestMethod);

        Headers requestHeaders = httpExchange.getResponseHeaders();
        requestHeaders.set("Content-Type", "text/html; charset=utf-8");
        requestHeaders.set("cache-control", "no-cache");

        httpExchange.sendResponseHeaders(200, 0); // 响应200, 返回0字节的响应体

        String s = "<h1>Hello, world.</h1><p>" + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss") + "</p>";

        try (OutputStream out = httpExchange.getResponseBody()) {
            out.write(s.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }


    @Override
    public void close() throws Exception {
        this.server.stop(0);
    }
}
