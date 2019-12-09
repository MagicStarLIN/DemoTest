package com.lcl.DataStructure.queue;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Node
 * @Description: 链表节点
 * @date 2019/12/9 10:17 上午
 */
public class Node<T> {
    private T data;
    private Node next;

    public Node(T data, Node next) {
        this.next = next;
        this.data = data;
    }

    public Node() {

    }

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
