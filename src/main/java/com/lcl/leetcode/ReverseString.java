package com.lcl.leetcode;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: ReverseString
 * @Description: 344. 反转字符串
 * @date 2019-08-28 14:47
 */
public class ReverseString {
    private void Swap(char A[], int i, int j) {
        char temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    private void reverseString(char[] s) {
        int i = 0;
        int j = s.length - 1;
        while (i < j) {
            Swap(s, i, j);
            i++;
            j--;
        }
    }
}
