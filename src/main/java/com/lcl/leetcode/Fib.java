package com.lcl.leetcode;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Fib
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/9/2 10:14 上午
 */
public class Fib {
    public int fib(int N) {
        if (N == 0) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        return fib(N - 1) + fib(N - 2);
    }
}
