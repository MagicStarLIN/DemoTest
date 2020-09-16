package com.lcl.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: UnreentrantLock
 * @date 2019-08-12 14:43
 */
public class UnreentrantLock {

    private AtomicReference<Thread> owner = new AtomicReference<Thread>();

    public void lock() {
        Thread current = Thread.currentThread();
        //这句是很经典的“自旋”语法，AtomicInteger中也有
        for (;;) {
            System.out.println("自旋");
            if (!owner.compareAndSet(null, current)) {
                return;
            }
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        owner.compareAndSet(current, null);
    }

    public static void main(String[] args) {
        UnreentrantLock unreentrantLock = new UnreentrantLock();
        unreentrantLock.lock();
        unreentrantLock.lock();

    }
}
