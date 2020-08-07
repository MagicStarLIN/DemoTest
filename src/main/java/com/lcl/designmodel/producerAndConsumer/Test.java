package com.lcl.designmodel.producerAndConsumer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: test
 * @date 2019-07-17 15:23
 */
public class Test {
    private final static int CAPACITY = 5;

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<Integer>();

        Thread pro1 = new Producer(queue,"P-1",CAPACITY);
        Thread pro2 = new Producer(queue,"P-2",CAPACITY);
        Thread con1 = new Consumer(queue,"C-1",CAPACITY);
        Thread con2 = new Consumer(queue,"C-2",CAPACITY);
        Thread con3 = new Consumer(queue,"C-3",CAPACITY);

        pro1.start();
        pro2.start();
        con1.start();
        con2.start();
        con3.start();

    }
}
