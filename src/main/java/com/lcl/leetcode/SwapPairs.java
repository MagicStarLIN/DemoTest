package com.lcl.leetcode;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: SwapPairs
 * @Description:
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例：给定 1->2->3->4, 你应该返回 2->1->4->3.
 * @date 2019/10/15 9:45 上午
 */
public class SwapPairs {
    private static ListNode swapPairs(ListNode head) {
        ListNode headNode = head;
        ListNode currentNode = head.next;
        ListNode nextNode;
        head = currentNode;
        int index = 0;
        while (currentNode.next != null) {
            if (index % 2 == 0) {
                nextNode = currentNode.next;
                currentNode.next = headNode;
                headNode.next = nextNode;

            }
            headNode = currentNode;
            currentNode = headNode.next;
            index++;
        }
        //TODO 第二次不交换情况下，还需要将两对节点之间进行连接
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        swapPairs(l1);
    }
}
