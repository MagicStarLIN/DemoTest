package com.lcl.leetcode;

import apple.laf.JRSUIUtils;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: SortedArrayToBST
 * @Description:将有序数组转换为二叉搜索树
 * @date 2019-08-19 11:31
 */
public class SortedArrayToBST {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return nums == null ? null : BuildTree(nums, 0, nums.length - 1);
    }

    private TreeNode BuildTree(int[] nums, int l, int r) {
        int m = (r - l)/2;
        if (l > r) {
            return null;
        }
        TreeNode root = new TreeNode(nums[m]);
        root.left = BuildTree(nums, l, m - 1);
        root.right = BuildTree(nums, m+1, r);
        return root;
    }
}

