package com.lcl.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: LimiterTest
 * @date 2021/10/25 1:11 上午
 */
public class LimiterTest {


    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final int THREAD_COUNT = 25;


    public static void testRateLimiter() {
        RateLimiter rateLimiter = RateLimiter.create(5);

        Thread[] ts = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            ts[i] = new Thread(new RateLimiterThread(rateLimiter), "RateLimiterThread-" + i);
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            ts[i].start();
        }

    }

    public static void testRateLimiterPermits() {
        RateLimiter rateLimiter = RateLimiter.create(1);

        System.out.println("获取1个令牌开始，时间为" + FORMATTER.format(new Date()));
        double cost = rateLimiter.acquire(1);
        System.out.println("获取1个令牌结束，时间为" + FORMATTER.format(new Date()) + ", 耗时" + cost + "ms");
        System.out.println("获取5个令牌开始，时间为" + FORMATTER.format(new Date()));
        cost = rateLimiter.acquire(5);
        System.out.println("获取5个令牌结束，时间为" + FORMATTER.format(new Date()) + ", 耗时" + cost + "ms");
        System.out.println("获取3个令牌开始，时间为" + FORMATTER.format(new Date()));
        cost = rateLimiter.acquire(3);
        System.out.println("获取3个令牌结束，时间为" + FORMATTER.format(new Date()) + ", 耗时" + cost + "ms");
    }


    public static void testSmoothWarmingUp() throws InterruptedException {
        RateLimiter r = RateLimiter.create(2, 3, TimeUnit.SECONDS);
        for (int i = 0; i < 5; i++) {
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("end");
        }
        Thread.sleep(10000);
        System.out.println("+++++++++++++++++++++++++");
        for (int i = 0; i < 5; i++) {
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("end");
        }
    }

    public static class RateLimiterThread implements Runnable {

        private final RateLimiter rateLimiter;

        public RateLimiterThread(RateLimiter rateLimiter) {
            this.rateLimiter = rateLimiter;
        }

        @Override
        public void run() {
            rateLimiter.acquire(1);
            System.out.println(Thread.currentThread().getName() +
                    "获取到了令牌，时间 = " + FORMATTER.format(new Date()));
        }

    }

    public static void main(String[] args) throws InterruptedException {
//        testRateLimiter();
        testRateLimiterPermits();
//        testSmoothWarmingUp();
    }

}
