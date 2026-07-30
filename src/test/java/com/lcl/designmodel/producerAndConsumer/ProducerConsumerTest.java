package com.lcl.designmodel.producerAndConsumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ProducerConsumerTest {

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void producerAndConsumerTerminatePromptlyWhenInterrupted() throws Exception {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
        queue.put(-1);
        Producer producer = new Producer(queue, "producer", 2);
        Consumer consumer = new Consumer(queue, "consumer", 2);
        producer.setDaemon(true);
        consumer.setDaemon(true);

        producer.start();
        consumer.start();
        try {
            long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(2);
            while (queue.contains(-1) && System.nanoTime() < deadline) {
                Thread.onSpinWait();
            }
            assertFalse(queue.contains(-1), "consumer did not take the sentinel value");

            producer.interrupt();
            consumer.interrupt();
            producer.join(1_000);
            consumer.join(1_000);

            assertFalse(producer.isAlive(), "producer did not terminate after interruption");
            assertFalse(consumer.isAlive(), "consumer did not terminate after interruption");
        } finally {
            producer.interrupt();
            consumer.interrupt();
        }
    }
}
