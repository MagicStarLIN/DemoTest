package com.lcl.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: TestDisruptorDemo
 * @Description: demo
 * @date 2023/5/28 16:10
 */
public class TestDisruptorDemo {
    public static void main(String[] args) throws InterruptedException {
        Disruptor<TestEvent> disruptor = new Disruptor<>(
                TestEvent::new,
                1024 * 1024,
                Executors.defaultThreadFactory(),
                ProducerType.MULTI,
                new YieldingWaitStrategy()
        );
//        disruptor.handleEventsWith(new TestEventHandler());
//        多消费者 重复消费
//        disruptor.handleEventsWith(new TestEventHandler(), new TestEventHandler());
//        Disruptor 4 removed worker pools; one handler preserves one consumption per event.
        disruptor.handleEventsWith(new TestEventHandler());
        disruptor.start();
        RingBuffer<TestEvent> ringBuffer = disruptor.getRingBuffer();
        TestEventProducer eventProducer = new TestEventProducer(ringBuffer);
//        eventProducer.onData(UUID.randomUUID().toString());

//        多个生产者
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(100);
        try {
            for (int i = 0; i < 100; i++) {
                fixedThreadPool.execute(() -> eventProducer.onData(UUID.randomUUID().toString()));
            }
            fixedThreadPool.shutdown();
            if (!fixedThreadPool.awaitTermination(1, TimeUnit.MINUTES)) {
                fixedThreadPool.shutdownNow();
            }
        } finally {
            fixedThreadPool.shutdownNow();
            disruptor.shutdown();
        }
    }
}
