package com.lcl.designmodel.proxy.proxydemo;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: ProxyDynamticImpl
 * @date 2019-07-11 13:52
 */
public class ResultSubject implements Subject {

    @Override
    public void doSomething() {
        System.out.println("do something");
    }
}
