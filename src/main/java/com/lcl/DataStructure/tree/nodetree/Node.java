package com.lcl.DataStructure.tree.nodetree;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Node
 * @Description: 树 节点类
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
