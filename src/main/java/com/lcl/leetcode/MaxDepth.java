package com.lcl.leetcode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName MaxDepth
 * @Description LeetCode 559. Maximum Depth of N-ary Tree
 * @Date 2021/4/30 4:57 下午
 * @Author liuchanglin
 * @Version 1.0
 **/
public class MaxDepth {

    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    private int solutation(Node root) {
        if (root == null) {
            return 0;
        } else if (root.children.isEmpty()) {
            return 1;
        } else {
            List<Integer> tempList = new LinkedList<>();

            for (Node child : root.children) {
                tempList.add(solutation(child));
            }
            return Collections.max(tempList) + 1;
        }
    }



}
