package com.lcl.leetcode;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: AddTwoNumbers
 * @Description: 2. 两数相加
 * @date 2019/10/14 10:01 上午
 */
public class AddTwoNumbers {
    /**
     * @Title addTwoNumbers
     * @Description
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     *如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 执行用时 :2 ms , 在所有 java 提交中击败了99.99%的用户
     * 内存消耗 :44.6 MB, 在所有 java 提交中击败了85.08%的用户
     * @Author liuchanglin
     * @Date 2019/10/14 3:21 下午
     * @Param [l1, l2]
     * @return com.lcl.leetcode.ListNode
     **/
    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean flag = false;
        ListNode result = new ListNode(0);
        ListNode head = result;
        while (l1 != null || l2 != null) {
            int value = 0;
            if (l1 == null && l2 != null) {
                value = l2.val;
                l2 = l2.next;
            }
            if (l2 == null && l1 != null) {
                value = l1.val;
                l1 = l1.next;
            }
            if (l1 != null && l2 != null) {
                value = l1.val + l2.val;
                l1 = l1.next;
                l2 = l2.next;
            }
            if (flag == true) {
                value++;
                flag = false;
            }
            if (value >= 10) {
                value = value % 10;
                flag = true;
            }
            result.next = new ListNode(value);
            result = result.next;
        }
        if (flag == true) {
            result.next = new ListNode(1);
        }
        return head.next;
    }

}
