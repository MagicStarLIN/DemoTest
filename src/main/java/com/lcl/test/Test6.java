package com.lcl.test;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test6
 * @Description: 京东笔试第一题，排队分组
 * @date 2019-08-24 19:38
 */
public class Test6 {
    private static int sortcount(ArrayList<Integer> list) {
        int count = 1;
        List newArr = new ArrayList();
        while (newArr.size()!=1 && newArr.size() != 2 ) {
             newArr = splitArray(list);
            count++;
        }
        return count;
    }

    private static int getMax(ArrayList<Integer> list) {
        int max = 0;
        int count = 0;
        for (Integer i : list) {
            if (max < i) {
                max = i;
            }
        }
        return list.indexOf(max);
    }

    private static List<Integer> splitArray(ArrayList<Integer> list) {
        return list.subList(0,getMax(list));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0 ; i < n; i++){
            int tmp = sc.nextInt();
            list.add(tmp);
        }
        System.out.println(sortcount(list));
    }
}
