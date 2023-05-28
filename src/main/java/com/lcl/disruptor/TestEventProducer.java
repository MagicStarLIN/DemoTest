package com.lcl.disruptor;

import com.lmax.disruptor.RingBuffer;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: TestEventProducer
 * @Description: 生产者类
 * @date 2023/5/28 15:56
 */
public class TestEventProducer {
    private final RingBuffer<TestEvent> ringBuffer;

    public TestEventProducer(RingBuffer<TestEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(String orderId) {
        long sequence = ringBuffer.next();
        try {
            TestEvent testEvent = ringBuffer.get(sequence);
            testEvent.setId(orderId);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
