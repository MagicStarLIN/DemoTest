package com.lcl.utils;

import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import org.apache.hc.core5.util.Timeout;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * HTTP utility methods used by the demos.
 */
public class HttpClientUtil {

    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 "
                    + "(KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";

    private static final RequestConfig REQUEST_CONFIG = RequestConfig.custom()
            .setConnectTimeout(Timeout.ofSeconds(30))
            .setConnectionRequestTimeout(Timeout.ofSeconds(30))
            .setResponseTimeout(Timeout.ofSeconds(30))
            .build();

    public static String getRequest(String url, Map<String, String> params) {
        HttpGet request = new HttpGet(url);
        try {
            URIBuilder builder = new URIBuilder(url);
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    builder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            request.setUri(builder.build());
            request.setConfig(REQUEST_CONFIG);
            request.setHeader("User-Agent", USER_AGENT);

            try (CloseableHttpClient client = HttpClients.createDefault();
                 CloseableHttpResponse response = client.execute(request)) {
                return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            }
        } catch (Exception exception) {
            throw requestFailure(request.getRequestUri(), exception);
        }
    }

    public static String postRequest(String url, Map<String, Object> params) {
        return postRequest(url, params, Collections.emptyMap());
    }

    public static String postRequest(
            String url, Map<String, Object> params, Map<String, Object> headers) {
        HttpPost request = new HttpPost(url);
        request.setConfig(REQUEST_CONFIG);
        if (headers == null || headers.isEmpty()) {
            request.setHeader("Content-Type", ContentType.APPLICATION_FORM_URLENCODED.toString());
        } else {
            addHeaders(request, headers);
        }

        if (params != null) {
            List<NameValuePair> parameterList = new ArrayList<>();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                parameterList.add(
                        new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
            }
            request.setEntity(new UrlEncodedFormEntity(parameterList, StandardCharsets.UTF_8));
        }

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(request)) {
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception exception) {
            throw requestFailure(request.getRequestUri(), exception);
        }
    }

    public static String postRequestJson(String url, String json, Map<String, Object> headers) {
        HttpPost request = new HttpPost(url);
        request.setConfig(REQUEST_CONFIG);
        if (headers == null || headers.isEmpty()) {
            request.setHeader("User-Agent", USER_AGENT);
        } else {
            addHeaders(request, headers);
        }
        request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(request)) {
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception exception) {
            throw requestFailure(request.getRequestUri(), exception);
        }
    }

    public static String deleteRequestJson(String url, String json, Map<String, Object> headers) {
        HttpDelete request = new HttpDelete(url);
        request.setConfig(REQUEST_CONFIG);
        if (headers == null || headers.isEmpty()) {
            request.setHeader("User-Agent", USER_AGENT);
        } else {
            addHeaders(request, headers);
        }

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(request)) {
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception exception) {
            throw requestFailure(request.getRequestUri(), exception);
        }
    }

    public static String requestOnce(final String url, String data, String rewardMchid)
            throws Exception {
        HttpPost request = new HttpPost(url);
        request.setConfig(REQUEST_CONFIG);
        request.setEntity(
                new StringEntity(
                        data, ContentType.create("text/xml", StandardCharsets.UTF_8)));
        request.addHeader("User-Agent", "wxpay sdk java v1.0 " + rewardMchid);

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(request)) {
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        }
    }

    private static void addHeaders(
            org.apache.hc.core5.http.HttpMessage request, Map<String, Object> headers) {
        for (Map.Entry<String, Object> entry : headers.entrySet()) {
            request.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
        }
    }

    private static IllegalStateException requestFailure(String requestUri, Exception cause) {
        return new IllegalStateException("HTTP request failed: " + requestUri, cause);
    }
}
