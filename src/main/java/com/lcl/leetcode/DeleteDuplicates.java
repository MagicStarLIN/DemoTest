package com.lcl.leetcode;


/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: DeleteDuplicates
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-08-05 09:50
 */
public class DeleteDuplicates {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode l1;
        ListNode l2;
        l1 = head;
        l2 = head.next;
        while (l1.next != null) {
            while (l2.next != null && l1.val == l2.val) {
                    l2 = l2.next;
            }
            l1.next = l2;
            l1 = l1.next;
        }
        return l1;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);
        head.next = l1;
        l1.next = l2;
        System.out.println(deleteDuplicates(head).val);
    }
}
