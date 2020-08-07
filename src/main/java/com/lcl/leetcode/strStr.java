package com.lcl.leetcode;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: strStr
 * @date 2019-07-24 10:57
 */
public class strStr {
    private static int strstr(String haystack, String needle) {
        if (needle.equals("")) {
            return 0;
        }
        int size = needle.length();
        int head = 0;
        while (true) {
            String str = haystack.substring(head,size);
            if (str.equals(needle)) {
                return head;
            }
            head++;
            size++;
            if (size > haystack.length()) {
                break;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(strstr("aa","aa"));
    }
}
