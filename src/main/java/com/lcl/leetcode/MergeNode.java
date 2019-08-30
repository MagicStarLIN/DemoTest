package com.lcl.leetcode;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: MergeNode
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-07-18 10:37
 */
public class MergeNode {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }
        if (l1.val > l2.val) {
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
        return null;
    }
}

