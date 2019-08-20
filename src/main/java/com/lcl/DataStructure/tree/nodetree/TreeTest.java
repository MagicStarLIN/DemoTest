package com.lcl.DataStructure.tree.nodetree;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: TreeTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-07-12 14:42
 */
public class TreeTest {
    private static Node createTree() {
        Node root = new Node(10);
        Node node2 = new Node(9);
        Node node3 = new Node(11);
        Node node4 = new Node(8);
        Node node5 = new Node(13);
        Node node6 = new Node(7);
        Node node7 = new Node(16);
        root.leftChild = node2;
        root.rightChild = node3;
        node2.leftChild = node4;
        node2.rightChild = node5;
        node3.leftChild = node6;
        node3.rightChild = node7;
        return root;
    }

    public static void main(String[] args) {
        Node root = createTree();
        INodeTreeimpl services = new INodeTreeimpl();
//        System.out.println("前序遍历");
//        services.preOrder(root);
//        System.out.println("中序遍历");
//        services.inOrder(root);
//        System.out.println("后序遍历");
//        services.postOrder(root);

        services.DeleteNode(9,root);
        services.preOrder(root);


    }
}
