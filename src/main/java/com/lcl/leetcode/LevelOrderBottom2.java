package com.lcl.leetcode;

import java.util.*;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: LevelOrderBottom2
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-08-13 11:11
 */
public class LevelOrderBottom2 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
        public List<List<Integer>> levelOrder (TreeNode root){
            if (root == null)
                return new ArrayList<>();
            List<List<Integer>> res = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int count = queue.size();
                List<Integer> list = new ArrayList<Integer>();
                while (count > 0) {
                    TreeNode node = queue.poll();
                    list.add(node.val);
                    if (node.left != null)
                        queue.add(node.left);
                    if (node.right != null)
                        queue.add(node.right);
                    count--;
                }
                res.add(list);
            }
            Collections.reverse(res);
            return res;
        }
    }

