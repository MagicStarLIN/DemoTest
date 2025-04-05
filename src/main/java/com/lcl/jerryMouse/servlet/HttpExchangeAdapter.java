package com.lcl.jerryMouse.servlet;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: HttpExchangeAdapter
 * @date 2025/3/21 01:26
 */
public class HttpExchangeAdapter implements HttpExchangeRequest, HttpExchangeResponse {

    final HttpExchange httpExchange;

    public HttpExchangeAdapter(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
    }

    @Override
    public String getRequestMethod() {
        return httpExchange.getRequestMethod();
    }

    @Override
    public URI getRequestURI() {
        return httpExchange.getRequestURI();
    }

    @Override
    public Headers getRequestHeaders() {
        return null;
    }

    @Override
    public InetSocketAddress getRemoteAddress() {
        return null;
    }

    @Override
    public InetSocketAddress getLocalAddress() {
        return null;
    }

    @Override
    public byte[] getRequestBody() throws IOException {
        return new byte[0];
    }

    @Override
    public Headers getResponseHeaders() {
        return httpExchange.getResponseHeaders();
    }

    @Override
    public void setResponseHeaders(int rCode, long length) throws IOException {
        httpExchange.sendResponseHeaders(rCode, length);
    }

    @Override
    public OutputStream getResponseBody() {
        return httpExchange.getResponseBody();
    }
}
