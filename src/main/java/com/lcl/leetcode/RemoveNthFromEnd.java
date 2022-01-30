package com.lcl.leetcode;

/**
 * @ClassName RemoveNthFromEnd
 * @Description 19. 删除链表的倒数第 N 个结点
 * @Date 2022/1/30 3:26 PM
 * @Author liuchanglin
 * @Version 1.0
 **/
public class RemoveNthFromEnd {

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了5.02%的用户
     * 通过测试用例：
     * 208 / 208
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode quickPointer = head;
        ListNode slowPointer = head;

        for (int i = 0; i < n; i++) {
            quickPointer = quickPointer.next;
        }
        if (quickPointer == null) {
            ListNode result = head.next;
            head.next = null;
            return result;
        }
        ListNode preNode = head;
        while (quickPointer != null) {
            quickPointer = quickPointer.next;
            preNode = slowPointer;
            slowPointer = slowPointer.next;
        }

        preNode.next = slowPointer.next;

        return head;
    }
}
