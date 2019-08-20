package com.lcl.designmodel.proxy.proxydemo;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: ProxyDynamticImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-07-11 13:52
 */
public class ResultSubject implements Subject {

    @Override
    public void doSomething() {
        System.out.println("do something");
    }
}
