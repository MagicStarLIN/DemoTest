package com.lcl.DataStructure.tree.nodetree;

import java.util.Stack;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: INodTreeimpl
 * @Description: 树的操作
 * @date 2019-07-12 10:49
 */
public class INodeTreeimpl implements INodeTree {
//
//    private Node root;
//
//    public INodeTreeimpl(Node root) {
//        this.root = root;
//    }

    @Override
    public Node findNode(int key,Node root) {
        //查找操作
        Node current = root;
        while (current != null) {
            if (key < current.data) {
                current = current.leftChild;
            } else if (key > current.data) {
                current = current.rightChild;
            } else {
                return current;
            }
        }
        return null;
    }

    @Override
    public Node insertNode(int key, Node root) {
        //插入操作
        if (root == null) {
            return new Node(key);
        }

        Node current = root;
        while (true) {
            if (key < current.data) {
                if (current.leftChild == null) {
                    current.leftChild = new Node(key);
                    return root;
                }
                current = current.leftChild;
            } else if (key > current.data) {
                if (current.rightChild == null) {
                    current.rightChild = new Node(key);
                    return root;
                }
                current = current.rightChild;
            } else {
                return root;
            }
        }
    }

    @Override
    public Node deleteNode(int key, Node root) {
        if (root == null) {
            return null;
        }
        if (key < root.data) {
            root.leftChild = deleteNode(key, root.leftChild);
        } else if (key > root.data) {
            root.rightChild = deleteNode(key, root.rightChild);
        } else {
            if (root.leftChild == null) {
                return root.rightChild;
            }
            if (root.rightChild == null) {
                return root.leftChild;
            }
            Node successor = minimum(root.rightChild);
            root.data = successor.data;
            root.rightChild = deleteNode(successor.data, root.rightChild);
        }
        return root;
    }

    private Node minimum(Node root) {
        Node current = root;
        while (current.leftChild != null) {
            current = current.leftChild;
        }
        return current;
    }

    @Override
    public void preOrder(Node root) {
        //前序遍历
        if (root == null) {
            return;
        }
        root.display();
        preOrder(root.leftChild);
        preOrder(root.rightChild);
    }

    @Override
    public void inOrder(Node root) {
        //中序遍历
        if (root == null) {
            return;
        }
        inOrder(root.leftChild);
        root.display();
        inOrder(root.rightChild);

    }

    @Override
    public void postOrder(Node root) {
        //后序遍历
        if (root == null) {
            return;
        }
        postOrder(root.leftChild);
        postOrder(root.rightChild);
        root.display();
    }

    @Override
    public void preOrderNoRecursive(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node index = root;
        while (!stack.empty() ||  index != null) {
            if (index != null) {
                index.display();
                stack.push(index);
                index = index.leftChild;
            } else {
                index = stack.peek();
                stack.pop();
                index = index.rightChild;
            }
        }
    }

    @Override
    public void inOrderNoRecursive(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node index = root;
        while (!stack.empty() ||  index != null) {
            if (index != null) {
                stack.push(index);
                index = index.leftChild;
            } else {
                index = stack.peek();
                stack.pop();
                index.display();
                index = index.rightChild;
            }
        }
    }

    @Override
    public void postOrderNoRecursive(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node index = root;
        stack.push(index);
        Node pre = stack.peek();
        while (!stack.isEmpty()) {
            index = stack.peek();
            if ((index.rightChild == null || pre == index.rightChild)
                    || (pre != null && (pre == index.rightChild || pre == index.leftChild))) {
                index.display();
                stack.pop();
                pre = index;
            } else {
                if (index.rightChild != null) {
                    stack.push(index.rightChild);
                }
                if (index.leftChild != null) {
                    stack.push(index.leftChild);
                }
            }
        }
    }
}
