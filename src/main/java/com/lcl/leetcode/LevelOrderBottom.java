package com.lcl.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: LevelOrderBottom
 * @Description: 102.二叉树的层次遍历
 * @date 2019-08-13 09:53
 */
public class LevelOrderBottom {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> arr = new ArrayList<>();
        List<Integer> newArr = new ArrayList<>();
        if (root == null) {
            return arr;
        }
        newArr.add(root.val);
        IndexTree(root, arr).add(newArr);
        return arr;
    }

    private List<List<Integer>> IndexTree(TreeNode root,List<List<Integer>> arr) {
        List<Integer> newArr = new ArrayList<>();
        if (root == null) {
            return arr;
        }
        if (root.left == null && root.right == null) {
            return arr;
        }
        if (root.right != null && root.left == null) {
            IndexTree(root.right, arr);
        }
        if (root.right == null && root.left != null) {
            IndexTree(root.left, arr);
        }
        IndexTree(root.left, arr);
        IndexTree(root.right, arr);
        if (root.left != null) newArr.add(root.left.val);
        if (root.right != null)newArr.add(root.right.val);
        arr.add(newArr);
        return arr;
    }
}
