package com.lcl.DataStructure.queue;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: ArrayQueue
 * @Description: 队列（数组实现）int
 * @date 2019/12/9 9:41 上午
 */
public class ArrayQueue {
    private int head = 0;
    private int tail = 0;
    private int[] queue;

    private int length;
    //初始化
    public ArrayQueue(int length) {
        this.length = length;
        queue = new int[length];
    }

    //入队
    public boolean enQueue(int item) {
        if (tail == length) {
            if (head == 0) {
                System.err.println("队列已满");
                return false;
            }
            //数据搬移
            for (int i = head; i < tail; i++) {
                queue[i - head] = queue[i];
            }
            tail -= head;
            head = 0;
        }
        queue[tail] = item;
        tail++;
        return true;
    }

    public int deQueue() {
        if (head == tail) {
            System.err.println("队列已空");
            return 0;
        }
        int result = queue[head];
        head++;
        return result;
    }

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(10);
        arrayQueue.enQueue(3);
        arrayQueue.enQueue(4);
        arrayQueue.enQueue(5);
        arrayQueue.enQueue(6);
        arrayQueue.enQueue(7);
        arrayQueue.enQueue(8);
        System.err.println(arrayQueue.deQueue());
        System.err.println(arrayQueue.deQueue());
        System.err.println(arrayQueue.deQueue());
    }
}
