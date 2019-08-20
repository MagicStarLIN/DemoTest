package com.lcl.DataStructure.tree.nodetree;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Node
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-07-12 10:40
 */
public class Node {
    int data;
    Node leftChild;
    Node rightChild;

    public Node(int data) {
        this.data = data;
    }

    public void display() {
        System.out.println(data);
    }
}
