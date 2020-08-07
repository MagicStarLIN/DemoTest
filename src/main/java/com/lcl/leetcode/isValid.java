package com.lcl.leetcode;

import java.util.Stack;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: isValid
 * @date 2019-07-24 10:09
 */
public class isValid {
    public boolean isvalid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (stack.empty()) {
                stack.push(aChar);
            } else if (charsValid(stack.peek(), aChar)) {
                stack.pop();
            } else {
                stack.push(aChar);
            }
        }
        return stack.size() == 0;
    }

    private boolean charsValid(char c1, char c2) {
        if (c1 == '(' && c2 == ')') {
            return true;
        }
        if (c1 == '{' && c2 == '}') {
            return true;
        }
        if (c1 == '[' && c2 == ']') {
            return true;
        }
        return false;
    }
}
