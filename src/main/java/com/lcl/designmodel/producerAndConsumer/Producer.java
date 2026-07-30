package com.lcl.designmodel.producerAndConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Producer
 * @date 2019-07-17 14:15
 */
public class Producer extends Thread {
    private final BlockingQueue<Integer> queue;
    private int i;

    public Producer(BlockingQueue<Integer> queue, String name, int maxSize) {
        super(name);
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                queue.put(i);
                System.out.println("[" + getName() + "] Producing value : " + i++);
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
            } catch (InterruptedException interrupted) {
                interrupt();
                return;
            }
        }
    }
}
