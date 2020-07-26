package com.lcl.designmodel.proxy.proxydemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: ProxyHandler
 * @date 2019-07-11 14:04
 */
public class ProxyHandler implements InvocationHandler {

    private Object proxied;

    public ProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxied,args);
    }
}
