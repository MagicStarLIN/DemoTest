package com.lcl.jerryMouse.seesion;

import com.lcl.jerryMouse.context.ServletContextImpl;
import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: SessionManager
 * @date 2025/4/6 00:20
 */
public class SessionManager implements Runnable{

    // 引用ServletContext:
    ServletContextImpl servletContext;
    // 持有SessionID -> Session:
    Map<String, HttpSessionImpl> sessions = new ConcurrentHashMap<>();
    // Session默认过期时间(秒):
    int inactiveInterval;

    public SessionManager(ServletContextImpl servletContext, int inactiveInterval) {
        this.servletContext = servletContext;
        this.inactiveInterval = inactiveInterval;
    }

    public HttpSession getSession(String sessionId) {
        HttpSessionImpl session = sessions.get(sessionId);
        if (session == null) {
            // Session未找到，创建一个新的Session:
            session = new HttpSessionImpl(this.servletContext, sessionId, inactiveInterval);
            sessions.put(sessionId, session);
        } else {
            session.lastAccessedTime = System.currentTimeMillis();
        }
        return session;
    }

    // 删除Session:
    public void remove(HttpSession session) {
        this.sessions.remove(session.getId());
    }

    @Override
    public void run() {
        for (;;) {
            // 每60秒扫描一次:
            try {
                Thread.sleep(60_000L);
            } catch (InterruptedException e) {
                break;
            }
            // 当前时间:
            long now = System.currentTimeMillis();
            // 遍历Session:
            for (String sessionId : sessions.keySet()) {
                HttpSession session = sessions.get(sessionId);
                // 判断是否过期:
                if (session.getLastAccessedTime() + session.getMaxInactiveInterval() * 1000L < now) {
                    // 删除过期的Session:
                    session.invalidate();
                }
            }
        }
    }
}
