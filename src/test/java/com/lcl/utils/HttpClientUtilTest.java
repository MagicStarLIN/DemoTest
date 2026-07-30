package com.lcl.utils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HttpClientUtilTest {

    private HttpServer server;
    private String baseUrl;

    @BeforeEach
    void startServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress("localhost", 0), 0);
        server.createContext("/query", exchange -> respond(exchange, exchange.getRequestURI().getRawQuery()));
        server.createContext("/form", exchange -> respond(exchange, requestBody(exchange)));
        server.createContext("/json", exchange -> respond(exchange, requestBody(exchange)));
        server.start();
        baseUrl = "http://localhost:" + server.getAddress().getPort();
    }

    @AfterEach
    void stopServer() {
        if (server != null) {
            server.stop(0);
        }
    }

    @Test
    void getRequestSendsQueryParameters() {
        assertEquals("name=codex",
                HttpClientUtil.getRequest(baseUrl + "/query", Map.of("name", "codex")));
    }

    @Test
    void postRequestSendsFormParameters() {
        assertEquals("name=codex",
                HttpClientUtil.postRequest(baseUrl + "/form", Map.of("name", "codex")));
    }

    @Test
    void getRequestEncodesReservedAndUnicodeQueryParameters() {
        assertEquals("message=%E4%BD%A0%E5%A5%BD%20%26%20codex",
                HttpClientUtil.getRequest(
                        baseUrl + "/query", Map.of("message", "你好 & codex")));
    }

    @Test
    void postRequestEncodesReservedAndUnicodeFormParameters() {
        assertEquals("message=%E4%BD%A0%E5%A5%BD+%26+codex",
                HttpClientUtil.postRequest(
                        baseUrl + "/form", Map.of("message", "你好 & codex")));
    }

    @Test
    void postRequestJsonSendsJsonBody() {
        assertEquals("{\"name\":\"codex\"}",
                HttpClientUtil.postRequestJson(
                        baseUrl + "/json", "{\"name\":\"codex\"}", Map.of()));
    }

    @Test
    void getRequestWrapsConnectionFailure() {
        IllegalStateException failure = assertThrows(IllegalStateException.class,
                () -> HttpClientUtil.getRequest("http://localhost:1/unreachable", Map.of()));
        assertInstanceOf(IOException.class, failure.getCause());
    }

    @Test
    void getRequestWrapsConstructionFailureWithOriginalCause() {
        IllegalStateException failure = assertThrows(IllegalStateException.class,
                () -> HttpClientUtil.getRequest("http://[invalid", Map.of()));
        assertInstanceOf(IllegalArgumentException.class, failure.getCause());
    }

    @Test
    void postRequestWrapsHeaderConfigurationFailureWithOriginalCause() {
        IllegalStateException failure = assertThrows(IllegalStateException.class,
                () -> HttpClientUtil.postRequest(
                        baseUrl + "/form", Map.of(), Collections.singletonMap(null, "invalid")));
        assertInstanceOf(NullPointerException.class, failure.getCause());
    }

    @Test
    void postRequestJsonWrapsEntityFailureWithOriginalCause() {
        IllegalStateException failure = assertThrows(IllegalStateException.class,
                () -> HttpClientUtil.postRequestJson(baseUrl + "/json", null, Map.of()));
        assertInstanceOf(NullPointerException.class, failure.getCause());
    }

    @Test
    void deleteRequestJsonWrapsConstructionFailureWithOriginalCause() {
        IllegalStateException failure = assertThrows(IllegalStateException.class,
                () -> HttpClientUtil.deleteRequestJson("http://[invalid", "{}", Map.of()));
        assertInstanceOf(IllegalArgumentException.class, failure.getCause());
    }

    private static String requestBody(HttpExchange exchange) throws IOException {
        return new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
    }

    private static void respond(HttpExchange exchange, String body) throws IOException {
        byte[] response = body.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, response.length);
        try (exchange; var output = exchange.getResponseBody()) {
            output.write(response);
        }
    }
}
