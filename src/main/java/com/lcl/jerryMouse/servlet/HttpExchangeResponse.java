package com.lcl.jerryMouse.servlet;

import com.sun.net.httpserver.Headers;

import java.io.IOException;
import java.io.OutputStream;

public interface HttpExchangeResponse {

    Headers getResponseHeaders();

    void setResponseHeaders(int rCode, long length) throws IOException;

    OutputStream getResponseBody();
}
