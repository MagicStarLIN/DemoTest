package com.lcl.DataStructure.queue;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: LinkedQueue
 * @Description: 队列（链表实现）int
 * @date 2019/12/9 10:10 上午
 */
public class LinkedQueue<T> {
    private Node<T> head = null;
    private Node<T> tail = null;

    private int length = 0;

    public LinkedQueue(int length) {
        this.length = length;
    }

    public boolean enQueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (tail == null) {
            head = newNode;
            tail = newNode;

        } else {
            tail.setNext(newNode);
            tail = tail.getNext();
        }
        return true;
    }

    public T deQueue() {
        if (head == null) {
            return null;
        }
        T result = head.getData();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        return result;
    }
}
