package com.lcl.designmodel.proxy.staticProxy;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 测试类
 */
public class App {
    public static void main(String[] args) {
//        AtomicReference<Integer>  owner = new AtomicReference<>();
//        Thread current = Thread.currentThread();
//        Thread newThread = new Thread();
//        owner.set(1);
//        boolean flag = owner.compareAndSet(1,2);
//        System.out.println(newThread);
//        System.out.println(current);
//        System.out.println(flag);
        AtomicInteger count = new AtomicInteger();
        for(;;){
            System.out.println(count.getAndIncrement());
            if (count.get() == 5) {
                return;
            }
        }
    }
}


