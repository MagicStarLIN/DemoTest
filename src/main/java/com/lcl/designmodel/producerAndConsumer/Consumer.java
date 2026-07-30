package com.lcl.designmodel.producerAndConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Consumer
 * @Description: 消费者
 * @date 2019-07-17 15:08
 */
public class Consumer extends Thread {
    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue, String name, int maxSize) {
        super(name);
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                int value = queue.take();
                System.out.println("[" + getName() + "] Consuming value : " + value);
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
            } catch (InterruptedException interrupted) {
                interrupt();
                return;
            }
        }
    }
}
