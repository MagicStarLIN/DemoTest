package com.lcl.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: TestDisruptorDemo
 * @Description: demo
 * @date 2023/5/28 16:10
 */
public class TestDisruptorDemo {
    public static void main(String[] args) {
        Disruptor<TestEvent> disruptor = new Disruptor<>(
                TestEvent::new,
                1024 * 1024,
                Executors.defaultThreadFactory(),
                ProducerType.SINGLE,
                new YieldingWaitStrategy()
        );
//        disruptor.handleEventsWith(new TestEventHandler());
//        多消费者 重复消费
//        disruptor.handleEventsWith(new TestEventHandler(), new TestEventHandler());
//        多消费者 只消费一次
        disruptor.handleEventsWithWorkerPool(new TestEventHandler(), new TestEventHandler());
        disruptor.start();
        RingBuffer<TestEvent> ringBuffer = disruptor.getRingBuffer();
        TestEventProducer eventProducer = new TestEventProducer(ringBuffer);
//        eventProducer.onData(UUID.randomUUID().toString());

//        多个生产者
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            fixedThreadPool.execute(() -> eventProducer.onData(UUID.randomUUID().toString()));
        }
    }
}
