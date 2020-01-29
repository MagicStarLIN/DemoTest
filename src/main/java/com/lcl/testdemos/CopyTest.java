package com.lcl.testdemos;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: CopyTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-08-13 14:05
 */
public class CopyTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        int[] value = new int[]{1, 2, 3};
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(value);
        atomicIntegerArray.getAndSet(0, 3);
        System.err.println(atomicIntegerArray.get(0));
        System.err.println(value[0]);

    }
}
