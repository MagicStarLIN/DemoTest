package com.lcl.DataStructure.tree.nodetree;

public interface INodeTree {
    //查找节点
    Node findNode(int key,Node root);

    //删除节点
    boolean DeleteNode(int key,Node root);

    //新增节点
    boolean InsertNode(int key,Node root);

    //前序遍历
    void preOrder(Node root);

    //中序遍历
    void inOrder(Node root);

    //后序遍历
    void postOrder(Node root);

    //前序遍历非递归
    void preOrderNoRecursive(Node root);

    //中序遍历非递归
    void inOrderNoRecursive(Node root);

    //后序遍历非递归
    void postOrderNoRecursive(Node root);



}
