package com.lcl.test;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test7
 * @date 2019/9/8 8:43 下午
 */
public class Test7 {
    private int getLength(int h,int n) {
        int s = h;
        for (int i = 0; i < n; i++) {
            h = h/2;
            s += h*2;
        }
        return s;
    }
}
