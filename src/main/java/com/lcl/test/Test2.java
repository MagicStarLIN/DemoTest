package com.lcl.test;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test2
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-08-13 09:21
 */
public class Test2 {
    private static void add(int i) {
        i += 1;
    }

    public static void main(String[] args) {
        int i = 1;
        add(i);
        System.out.println(i);
    }
}
