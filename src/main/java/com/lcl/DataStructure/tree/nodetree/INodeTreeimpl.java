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
            if (current.data > key) {
                current = current.leftChild;
            }
            if (current.data < key) {
                current = current.rightChild;
            }else {
                return current;
            }
        }
        return null;
    }

    @Override
    public boolean InsertNode(int key,Node root) {
        //插入操作
        Node newNdoe = new Node(key);
        Node current = root;
        if (root == null) {
            root = newNdoe;
            return true;
        } else {
            while (current != null) {
                if (current.data > key) {
                    current = current.leftChild;
                    if(current == null) {
                        current = newNdoe;
                        return true;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        current = newNdoe;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean DeleteNode(int key,Node root) {
        //删除操作
        Node current = root;
        Node parent = root;
        boolean isLeftChild = false;
        //[1]找到需要删除的节点
        while (current.data != key) {
            parent = current;
            if (current.data > key) {
                current = current.leftChild;
                isLeftChild = true;
            } else {
                current = current.rightChild;
                isLeftChild = false;
            }
            if (current == null) {
                return false;
            }
        }
        //[2]删除操作第一种情况，需要删除的节点没有子节点
        if (current.leftChild == null && current.rightChild == null) {
            if (isLeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }

        }
        //[3]删除操作第二种情况，需要删除的节点有一个子节点
        if (current.leftChild == null && current.rightChild != null) {
            if (current == root) {
                root = current.rightChild;
            } else if (isLeftChild) {
                parent.leftChild = current.rightChild;
            } else {
                parent.rightChild = current.rightChild;
            }
            return true;
        }
        if (current.leftChild != null && current.rightChild == null) {
            if (current == root) {
                root = current.leftChild;
            } else if (isLeftChild) {
                parent.leftChild = current.leftChild;
            } else {
                parent.rightChild = current.leftChild;
            }
            return true;
        }
        //[4]删除操作第三种情况，需要删除的节点有两个子节点
        //将被删除节点的右子树中的最小节点与其替换
        if (current.leftChild != null && current.rightChild != null) {
            //4.1 找到右子树中的最小节点
            Node minNode = current.rightChild;
            Node minNodeparent = current;
            while (minNode != null) {
                minNodeparent = minNode;
                minNode = minNode.leftChild;
            }
            //4.2 进行替换
            current.data = minNode.data;
            minNodeparent.leftChild = null;
            return true;
        }
        return false;
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
