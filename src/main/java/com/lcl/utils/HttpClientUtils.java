package com.lcl.utils;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.util.Timeout;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Shared pooled HTTP client used by long-lived demos.
 */
public class HttpClientUtils {

    private static final PoolingHttpClientConnectionManager CONNECTION_MANAGER =
            new PoolingHttpClientConnectionManager();
    private static final RequestConfig REQUEST_CONFIG = RequestConfig.custom()
            .setConnectTimeout(Timeout.ofSeconds(30))
            .setConnectionRequestTimeout(Timeout.ofSeconds(30))
            .setResponseTimeout(Timeout.ofSeconds(30))
            .build();
    private static final CloseableHttpClient CLIENT;

    static {
        CONNECTION_MANAGER.setMaxTotal(1000);
        CONNECTION_MANAGER.setDefaultMaxPerRoute(20);
        CLIENT = HttpClients.custom()
                .setConnectionManager(CONNECTION_MANAGER)
                .setConnectionManagerShared(true)
                .setDefaultRequestConfig(REQUEST_CONFIG)
                .build();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                CLIENT.close();
            } catch (IOException exception) {
                System.err.println("Unable to close HTTP client: " + exception.getMessage());
            } finally {
                CONNECTION_MANAGER.close();
            }
        }, "http-client-shutdown"));
    }

    public static CloseableHttpClient getHttpClient() {
        return CLIENT;
    }

    public static String getHttpEntiy(CloseableHttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        if (entity == null) {
            return null;
        }
        try {
            return EntityUtils.toString(entity, StandardCharsets.UTF_8);
        } catch (ParseException exception) {
            throw new IOException("Unable to parse HTTP response entity", exception);
        }
    }
}
