package com.lcl.leetcode;

/**
 * @ClassName GetKthFromEnd
 * @Description 剑指 Offer 22. 链表中倒数第k个节点 LCOV
 * @Date 2021/4/30 6:03 下午
 * @Author liuchanglin
 * @Version 1.0
 **/
public class GetKthFromEnd {

    //  Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private ListNode solutation(ListNode head, int k) {
        ListNode slow = head;
        ListNode temp = head;
        ListNode fast = head;
        for (int i = 1; i < k; i++) {
            fast = temp.next;
            temp = fast;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
