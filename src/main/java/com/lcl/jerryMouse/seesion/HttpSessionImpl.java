package com.lcl.jerryMouse.seesion;

import com.lcl.jerryMouse.context.ServletContextImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;

import java.util.Enumeration;
import java.util.jar.Attributes;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: HttpSessionImpl
 * @date 2025/4/6 00:31
 */
public class HttpSessionImpl implements HttpSession {

    ServletContextImpl servletContext;

    String sessionId;

    long creationTime;

    long lastAccessedTime;

    int maxInactiveInterval; // 过期时间(s)

    Attributes attributes;

    public HttpSessionImpl(ServletContextImpl servletContext, String sessionId, long creationTime) {
        this.servletContext = servletContext;
        this.sessionId = sessionId;
        this.creationTime = creationTime;
    }

    @Override
    public long getCreationTime() {
        return 0;
    }

    @Override
    public String getId() {
        return "";
    }

    @Override
    public long getLastAccessedTime() {
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        return servletContext;
    }

    @Override
    public void setMaxInactiveInterval(int i) {

    }

    @Override
    public int getMaxInactiveInterval() {
        return 0;
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
    public void setAttribute(String s, Object o) {

    }

    @Override
    public void removeAttribute(String s) {

    }

    @Override
    public void invalidate() {
        // 从SessionManager中移除:
        this.servletContext.getSessionManager().remove(this);
        this.sessionId = null;
    }
    @Override
    public boolean isNew() {
        return false;
    }
}
