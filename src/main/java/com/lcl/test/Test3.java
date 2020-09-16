package com.lcl.test;

import java.util.Scanner;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test3
 * @date 2019-08-03 16:53
 */
public class Test3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int tmp = sc.nextInt();
            arr[i] = tmp;
        }
        boolean flag = false;
        for (int i : arr) {
            if (i%2==0){
            }
        }
    }
}
