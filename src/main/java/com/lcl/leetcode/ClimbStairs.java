package com.lcl.leetcode;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: ClimbStairs
 * @Description: 动态规划爬楼梯
 * @date 2019-08-20 09:44
 */
public class ClimbStairs {
    public static int climbStairs(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int a = 1;
        int b = 2;
        int temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = a + b;
            a = b;
            b = temp;
        }
        return temp;
    }

    public static void main(String[] args) {
        System.err.println(climbStairs(5));

    }

}
