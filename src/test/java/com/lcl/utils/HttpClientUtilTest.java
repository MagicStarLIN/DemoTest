package com.lcl.utils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.apache.hc.client5.http.HttpResponseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HttpClientUtilTest {

    private HttpServer server;
    private String baseUrl;
    private CountDownLatch truncatedResponseClosed;

    @BeforeEach
    void startServer() throws IOException {
        truncatedResponseClosed = new CountDownLatch(1);
        server = HttpServer.create(new InetSocketAddress("localhost", 0), 0);
        server.createContext("/query", exchange -> respond(exchange, exchange.getRequestURI().getRawQuery()));
        server.createContext("/form", exchange -> respond(exchange, requestBody(exchange)));
        server.createContext("/json", exchange -> respond(exchange, requestBody(exchange)));
        server.createContext("/delete", HttpClientUtilTest::respondWithContentTypeAndBody);
        server.createContext("/status-500", exchange -> respond(exchange, 500, "server error"));
        server.createContext("/truncated", this::respondWithTruncatedBody);
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
    void getRequestRejectsNonOkStatusWithOriginalCause() {
        IllegalStateException failure = assertThrows(IllegalStateException.class,
                () -> HttpClientUtil.getRequest(baseUrl + "/status-500", Map.of()));

        HttpResponseException cause =
                assertInstanceOf(HttpResponseException.class, failure.getCause());
        assertEquals(500, cause.getStatusCode());
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void getRequestWrapsTruncatedResponseWithOriginalCause() throws InterruptedException {
        IllegalStateException failure = assertThrows(IllegalStateException.class,
                () -> HttpClientUtil.getRequest(baseUrl + "/truncated", Map.of()));

        assertInstanceOf(IOException.class, failure.getCause());
        assertTrue(truncatedResponseClosed.await(2, TimeUnit.SECONDS));
    }

    @Test
    void getRequestFailureDoesNotDiscloseUserInfoOrQueryToken() {
        String secret = "token-value-must-not-leak";
        IllegalStateException failure = assertThrows(IllegalStateException.class,
                () -> HttpClientUtil.getRequest(
                        "http://user:" + secret + "@[invalid/path?token=" + secret, Map.of()));

        assertInstanceOf(IllegalArgumentException.class, failure.getCause());
        assertFalse(failure.getMessage().contains(secret));
        assertFalse(failure.getMessage().contains("user:"));
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

    @Test
    void deleteRequestJsonSendsUtf8JsonBody() {
        assertEquals(
                "application/json; charset=UTF-8\n{\"message\":\"你好\"}",
                HttpClientUtil.deleteRequestJson(
                        baseUrl + "/delete", "{\"message\":\"你好\"}", Map.of()));
    }

    private static String requestBody(HttpExchange exchange) throws IOException {
        return new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
    }

    private static void respond(HttpExchange exchange, String body) throws IOException {
        respond(exchange, 200, body);
    }

    private static void respond(HttpExchange exchange, int status, String body) throws IOException {
        byte[] response = body.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(status, response.length);
        try (exchange; var output = exchange.getResponseBody()) {
            output.write(response);
        }
    }

    private static void respondWithContentTypeAndBody(HttpExchange exchange) throws IOException {
        String contentType = exchange.getRequestHeaders().getFirst("Content-Type");
        respond(exchange, contentType + "\n" + requestBody(exchange));
    }

    private void respondWithTruncatedBody(HttpExchange exchange) throws IOException {
        byte[] partialResponse = "partial".getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, partialResponse.length + 32L);
        try (exchange; var output = exchange.getResponseBody()) {
            output.write(partialResponse);
        } finally {
            truncatedResponseClosed.countDown();
        }
    }
}
