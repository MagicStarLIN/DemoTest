package com.lcl.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: TestEventHandler
 * @Description: 消费者类
 * @date 2023/5/28 16:06
 */
public class TestEventHandler implements EventHandler<TestEvent> {
    @Override
    public void onEvent(TestEvent testEvent, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("threadName: " + Thread.currentThread().getName() + ", event: " + testEvent + ", sequence: " + sequence + ", endOfBatch: " + endOfBatch);
    }
}
