package com.lcl.jerryMouse.servlet;

import java.net.URI;

public interface HttpExchangeRequest {

    String getRequestMethod();

    URI getRequestURI();
}
