package com.lcl.leetcode;

/**
 * @ClassName IsValidBST
 * @Description 98. Validate Binary Search Tree
 * @Date 2021/5/22 4:41 下午
 * @Author liuchanglin
 * @Version 1.0
 **/
public class IsValidBST {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public boolean Solution(TreeNode root) {
        return isValidateNode(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidateNode(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }

        if (node.val <= min || node.val >= max) {
            return false;
        }

        return isValidateNode(node.left, min, node.val) && isValidateNode(node.right, node.val, max);

    }
}


