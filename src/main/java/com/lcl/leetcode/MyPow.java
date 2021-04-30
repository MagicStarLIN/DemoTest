package com.lcl.leetcode;

/**
 * @ClassName MyPow
 * @Description
 * @Date 2021/4/30 1:43 下午
 * @Author liuchanglin
 * @Version 1.0
 **/
public class MyPow {

    private double solution(double x, int n) {
        boolean sign = n >= 0;
        long N = Math.abs((long) n);
        return sign ? quickMul(x, N) : 1.0 / quickMul(x, N);
    }

    private double quickMul(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double y = quickMul(x, n / 2);
        return n % 2 == 0 ? y * y : y * y * x;
    }

    private double solution2(double x, int n) {
        boolean sign = n >= 0;
        long N = Math.abs((long) n);
        return sign ? quickMul2(x, N) : 1.0 / quickMul2(x, N);
    }

    private double quickMul2(double x, long n) {
        double res = 1.0;
        double xContribute = x;
        while (n > 0) {
            if (n % 2 == 1) {
                res *= xContribute;
            }
            xContribute *= xContribute;
            n /= 2;
        }
        return res;
    }

}
