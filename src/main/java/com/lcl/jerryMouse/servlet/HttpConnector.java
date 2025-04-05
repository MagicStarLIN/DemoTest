package com.lcl.jerryMouse.servlet;

import com.lcl.jerryMouse.context.ServletContextImpl;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: HttpConnector
 * @date 2025/3/21 01:32
 */
public class HttpConnector implements HttpHandler, AutoCloseable {

    final HttpServer server;
    final ServletContextImpl servletContext;

    public HttpConnector() throws IOException {
        this.server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
        this.server.createContext("/", this);
        this.servletContext = new ServletContextImpl();
//        this.servletContext.initialize(); todo initialize servlets
        this.server.start();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        HttpExchangeAdapter httpExchangeAdapter = new HttpExchangeAdapter(httpExchange);
        HttpServletResponseImpl response = new HttpServletResponseImpl(httpExchangeAdapter);
        HttpServletRequestImpl request = new HttpServletRequestImpl(this.servletContext, httpExchangeAdapter, response);

        process(request, response);
    }

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // TODO do some print
        String name = request.getParameter("name");
        String html = "<h1>Hello, " + (name == null ? "world" : name) + ".</h1>";
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.write(html);
        pw.close();
    }

    @Override
    public void close() throws Exception {
        this.server.stop(3);
    }

    // jdk 21 required
    public static void main(String[] args) throws IOException {
        new HttpConnector();
    }
}
