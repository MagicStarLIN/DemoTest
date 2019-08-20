package com.lcl.test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test1
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-08-03 15:21
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] SocreArr = new int[n];
        for (int i = 0; i < n; i++) {
            int tmp = scanner.nextInt();
            System.out.println(tmp);
            SocreArr[i] = tmp;
        }

        Scanner scanner1 = new Scanner(System.in);
        int q = scanner1.nextInt();
        int[] index = new int[q];
        for (int i = 0; i < q; i++) {
            Scanner sc = new Scanner(System.in);
            int tmp = sc.nextInt();
            index[i] = tmp;
        }
        for (int i : index) {
            int socre = SocreArr[i];
            int count = 0;
            for (int j = 0; j < SocreArr.length; j++) {
                if (socre < SocreArr[i]){
                    count++;
                }
            }

            System.out.println(String.format("%.6f",(double)count/n*100));
        }

    }
}
