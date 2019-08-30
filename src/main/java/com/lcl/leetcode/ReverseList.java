package com.lcl.leetcode;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: ReverseList
 * @Description: 链表反转迭代与递归
 * @date 2019-08-21 09:57
 */
public class ReverseList {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    /*
     * @Title reverseListwithcycle
     * @Description
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗 :35.9 MB, 在所有 Java 提交中击败了56.12%的用户
     * @Date 2019-08-21 10:27
     * @Param [head]
     * @return com.lcl.leetcode.ReverseList.ListNode
     **/
    public ListNode reverseListwithcycle(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode headNode = head;
        ListNode currentNode = head.next;
        ListNode nextNode;
        while (currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = headNode;
            headNode = currentNode;
            currentNode = nextNode;
        }
        head.next = null;
        return headNode;
    }
    /*
     * @Title reverseListwithRecursion
     * @Description
     * 执行用时 : 1 ms, 在所有 Java 提交中击败了86.19%的用户
     * 内存消耗 :36.1 MB, 在所有 Java 提交中击败了55.82%的用户
     * @Date 2019-08-21 11:10
     * @Param [head]
     * @return com.lcl.leetcode.ReverseList.ListNode
     **/
    public ListNode reverseListwithRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = head;
        ListNode headNode = reverseListwithRecursion(node.next);
        node.next.next = node.next;
        node.next = null;
        return headNode;
    }
}
