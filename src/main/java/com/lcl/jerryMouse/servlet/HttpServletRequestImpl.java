package com.lcl.jerryMouse.servlet;

import com.google.common.collect.Maps;
import com.lcl.jerryMouse.context.HttpHeaders;
import com.lcl.jerryMouse.context.Parameters;
import com.lcl.jerryMouse.context.ServletContextImpl;
import com.lcl.jerryMouse.utils.HttpUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: HttpServletRequestImpl
 * @date 2025/3/21 01:45
 */
public class HttpServletRequestImpl implements HttpServletRequest {

    final ServletContextImpl servletContext;
    final HttpExchangeRequest exchangeRequest;
    final HttpServletResponse response;
    final HttpHeaders headers;
    final Parameters parameters;

//    public HttpServletRequestImpl(HttpExchangeRequest exchangeRequest) {
//        this.exchangeRequest = exchangeRequest;
//    }

    public HttpServletRequestImpl(ServletContextImpl servletContext, HttpExchangeRequest exchangeRequest, HttpServletResponse response) {
        this.servletContext = servletContext;
        this.exchangeRequest = exchangeRequest;
        this.response = response;
        this.headers = new HttpHeaders(exchangeRequest.getRequestHeaders());
        this.parameters = new Parameters(exchangeRequest, "UTF-8");
    }


    @Override
    public String getParameter(String s) {
        String query = this.exchangeRequest.getRequestURI().getRawQuery();
        if (StringUtils.isNotBlank(query)) {
            try {
                Map<String, String> map = parseQuery(query);
                return map.get(s);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    Map<String, String> parseQuery(String query) throws UnsupportedEncodingException {
        if (query == null || query.isEmpty()) {
            return Maps.newHashMap();
        }
        String[] ss = Pattern.compile("\\&").split(query);
        Map<String, String> map = new HashMap<>();
        for (String s : ss) {
            int n = s.indexOf('=');
            if (n >= 1) {
                String key = s.substring(0, n);
                String value = s.substring(n + 1);
                map.putIfAbsent(key, URLDecoder.decode(value, StandardCharsets.UTF_8.name()));
            }
        }
        return map;
    }

    @Override
    public String getAuthType() {
        return "";
    }

    @Override
    public Cookie[] getCookies() {
        String cookieValue = this.getHeader("Cookie");
        return HttpUtils.parseCookies(cookieValue);
    }

    @Override
    public long getDateHeader(String s) {
        return 0;
    }

    @Override
    public String getHeader(String name) {
        return this.headers.getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        List<String> hs = this.headers.getHeaders(name);
        if (hs == null) {
            return Collections.emptyEnumeration();
        }
        return Collections.enumeration(hs);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        return Collections.enumeration(this.headers.getHeaderNames());
    }

    @Override
    public int getIntHeader(String s) {
        return 0;
    }

    @Override
    public String getMethod() {
        return "";
    }

    @Override
    public String getPathInfo() {
        return "";
    }

    @Override
    public String getPathTranslated() {
        return "";
    }

    @Override
    public String getContextPath() {
        return "";
    }

    @Override
    public String getQueryString() {
        return "";
    }

    @Override
    public String getRemoteUser() {
        return "";
    }

    @Override
    public boolean isUserInRole(String s) {
        return false;
    }

    @Override
    public Principal getUserPrincipal() {
        return null;
    }

    @Override
    public String getRequestedSessionId() {
        return "";
    }

    @Override
    public String getRequestURI() {
        return "";
    }

    @Override
    public StringBuffer getRequestURL() {
        return null;
    }

    @Override
    public String getServletPath() {
        return "";
    }

    @Override
    public HttpSession getSession(boolean create) {
        String sessionId = null;
        // 获取所有Cookie:
        Cookie[] cookies = getCookies();
        if (cookies != null) {
            // 查找JSESSIONID:
            for (Cookie cookie : cookies) {
                if ("JSESSIONID".equals(cookie.getName())) {
                    // 拿到Session ID:
                    sessionId = cookie.getValue();
                    break;
                }
            }
        }
        // 未获取到SessionID，且create=false，返回null:
        if (sessionId == null && !create) {
            return null;
        }
        // 未获取到SessionID，但create=true，创建新的Session:
        if (sessionId == null) {
            // 如果Header已经发送，则无法创建Session，因为无法添加Cookie:
            if (this.response.isCommitted()) {
                throw new IllegalStateException("Cannot create session for response is commited.");
            }
            // 创建随机字符串作为SessionID:
            sessionId = UUID.randomUUID().toString();
            // 构造一个名为JSESSIONID的Cookie:
            String cookieValue = "JSESSIONID=" + sessionId + "; Path=/; SameSite=Strict; HttpOnly";
            // 添加到HttpServletResponse的Header:
            this.response.addHeader("Set-Cookie", cookieValue);
        }
        // 返回一个Session对象:
        return this.servletContext.getSessionManager().getSession(sessionId);
    }

    @Override
    public HttpSession getSession() {
        return null;
    }

    @Override
    public String changeSessionId() {
        return "";
    }

    @Override
    public boolean isRequestedSessionIdValid() {
        return false;
    }

    @Override
    public boolean isRequestedSessionIdFromCookie() {
        return false;
    }

    @Override
    public boolean isRequestedSessionIdFromURL() {
        return false;
    }

    @Override
    public boolean authenticate(HttpServletResponse httpServletResponse) throws IOException, ServletException {
        return false;
    }

    @Override
    public void login(String s, String s1) throws ServletException {

    }

    @Override
    public void logout() throws ServletException {

    }

    @Override
    public Collection<Part> getParts() throws IOException, ServletException {
        return Collections.emptyList();
    }

    @Override
    public Part getPart(String s) throws IOException, ServletException {
        return null;
    }

    @Override
    public <T extends HttpUpgradeHandler> T upgrade(Class<T> aClass) throws IOException, ServletException {
        return null;
    }

    @Override
    public Object getAttribute(String s) {
        return null;
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return null;
    }

    @Override
    public String getCharacterEncoding() {
        return "";
    }

    @Override
    public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

    }

    @Override
    public int getContentLength() {
        return 0;
    }

    @Override
    public long getContentLengthLong() {
        return 0;
    }

    @Override
    public String getContentType() {
        return "";
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return null;
    }

    @Override
    public String[] getParameterValues(String s) {
        return new String[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return Collections.emptyMap();
    }

    @Override
    public String getProtocol() {
        return "";
    }

    @Override
    public String getScheme() {
        return "";
    }

    @Override
    public String getServerName() {
        return "";
    }

    @Override
    public int getServerPort() {
        return 0;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return null;
    }

    @Override
    public String getRemoteAddr() {
        return "";
    }

    @Override
    public String getRemoteHost() {
        return "";
    }

    @Override
    public void setAttribute(String s, Object o) {

    }

    @Override
    public void removeAttribute(String s) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return null;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String s) {
        return null;
    }

    @Override
    public int getRemotePort() {
        return 0;
    }

    @Override
    public String getLocalName() {
        return "";
    }

    @Override
    public String getLocalAddr() {
        return "";
    }

    @Override
    public int getLocalPort() {
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    public AsyncContext startAsync() throws IllegalStateException {
        return null;
    }

    @Override
    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
        return null;
    }

    @Override
    public boolean isAsyncStarted() {
        return false;
    }

    @Override
    public boolean isAsyncSupported() {
        return false;
    }

    @Override
    public AsyncContext getAsyncContext() {
        return null;
    }

    @Override
    public DispatcherType getDispatcherType() {
        return null;
    }

    @Override
    public String getRequestId() {
        return "";
    }

    @Override
    public String getProtocolRequestId() {
        return "";
    }

    @Override
    public ServletConnection getServletConnection() {
        return null;
    }
}
