package com.lcl.leetcode;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: ConvertZStyle
 * @Description: 6. ZigZag Conversion
 * @date 2021-03-27 15:11
 */
public class ConvertZStyle {
    public static String solution(String s, int numRows) {
        String result = "";
        if (s == null || s.length() == 0 || s.length() == 1) {
            return s;
        }
        char[] arr = s.toCharArray();
        int threshold = 2 * (numRows - 1);
        if (threshold == 0) {
            return s;
        }
        for (int i = 0; i < numRows; i++) {
            int index = numRows - i - 1;
            if (index == 0 || index == (numRows - 1)) {
                for (int j = i; j < arr.length; ) {
                    result += arr[j];
                    j += threshold;
                }
                if (index == 0) {
                    break;
                }
            } else {
                for (int j = i; j < arr.length; ) {
                    result += arr[j];
                    j += 2 * index;
                    if (j < arr.length) {
                        result += arr[j];
                        j += 2 * i;
                    }
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        String s = "AB";
        System.out.println(solution(s, 1));
    }
}





/*
P A Y P A L I S H I R I N G
0   4   8   12
P   A   H   N
1 3 5 7 9 11 13
A P L S I I G
2   6   10
Y   I   R
PINALSIGYAHRPI
PINALIGYAIHRNPHG


0 6 12
1 5 7
 */