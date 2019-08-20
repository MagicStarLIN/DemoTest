package com.lcl.designmodel.proxy.proxydemo;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: DynamicProxy
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-07-11 14:08
 */
public class DynamicProxy {
    public static void main(String[] args) {
        ResultSubject real = new ResultSubject();

        Subject proxySubject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(),
                new Class[]{Subject.class},
                new ProxyHandler(real));

        proxySubject.doSomething();

    }


}
