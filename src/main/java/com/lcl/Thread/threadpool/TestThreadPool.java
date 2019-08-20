package com.lcl.Thread.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: TestThreadPool
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-07-15 10:59
 */
public class TestThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 4; i++) {
            executorService.submit(getTask());

        }
    }
    private static Runnable getTask() {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
    }
}
