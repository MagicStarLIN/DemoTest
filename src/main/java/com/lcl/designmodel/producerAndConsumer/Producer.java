package com.lcl.designmodel.producerAndConsumer;

import java.util.Queue;
import java.util.Random;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Producer
 * @date 2019-07-17 14:15
 */
public class Producer extends Thread{
    private Queue<Integer> queue;
    String name;
    int maxSize;
    int i = 0;

    public Producer(Queue<Integer> queue, String name, int maxSize) {
        super(name);
        this.queue = queue;
        this.name = name;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.size() == maxSize) {
                    try {
                        System.out.println("Queue is full, Producer[" + name + "] thread waiting for " + "consumer to take something from queue.");
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("[" + name + "] Producing value : +" + i);
                queue.offer(i++);
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
