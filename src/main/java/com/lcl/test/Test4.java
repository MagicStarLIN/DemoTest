package com.lcl.test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test4
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-08-03 16:19
 */
public class Test4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        int times = sc.nextInt();
        int[] arr = new int[length];
        int[] xs = new int[times];
        for (int i = 0; i < length; i++) {
            int tmp = sc.nextInt();
            arr[i] = tmp;
        }
        for (int i = 0; i < times; i++) {
            int tmp = sc.nextInt();
            xs[i] = tmp;
        }
        System.out.println(length);
        System.out.println(times);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(xs));
        for (int i = 0; i < xs.length; i++) {
            int count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (xs[i] <= arr[j]) {
                    count += 1;
                    arr[j] -= 1;
                }
            }
            System.out.println(count);
        }
    }
}
