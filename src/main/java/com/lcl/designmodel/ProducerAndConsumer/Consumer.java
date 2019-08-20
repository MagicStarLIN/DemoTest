package com.lcl.designmodel.ProducerAndConsumer;

import java.util.Queue;
import java.util.Random;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Consumer
 * @Description: 消费者
 * @date 2019-07-17 15:08
 */
public class Consumer extends Thread {
    private Queue<Integer> queue;
    String name;
    int maxSize;

    public Consumer(Queue<Integer> queue, String name, int maxSize) {
        this.queue = queue;
        this.name = name;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                        System.out.println("Queue is empty, Consumer[" + name + "] thread is waiting for Producer");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                int x = queue.poll();
                System.out.println("[" + name + "] Consuming value : " + x);
                queue.notifyAll();

                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
