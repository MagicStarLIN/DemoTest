package com.lcl.leetcode;

import java.util.Stack;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: IsPalindrome
 * @Description: 回文链表
 * @date 2019/8/30 4:33 下午
 */
public class IsPalindrome {
    //栈
    public boolean isPalindrome1(ListNode head) {
        if (head == null) {
            return true;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode currentNode = head;
        ListNode compareNode = head;
        while (currentNode != null) {
            stack.push(currentNode);
            currentNode = currentNode.next;
        }
        while (true) {
            if (stack.empty()) {
                return true;
            }
            if (stack.peek().val != compareNode.val) {
                return false;
            } else {
                compareNode = compareNode.next;
                stack.pop();
            }
        }
    }
    //快慢指针
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode pre = null;
        ListNode next;
        ListNode current = head;
        while (current != slow) {
            next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        if (fast != null) {
            slow = slow.next;
        }
        while (pre != null) {
            if (pre.val != slow.val) {
                return false;
            }
            pre = pre.next;
            slow = slow.next;
        }
        return true;
    }
}
