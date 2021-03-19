package com.lcl.test;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Executors;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test5
 * @date 2019-08-06 17:59
 */
public class Test5 {


    ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));


    public static void swap(Student x, Student y) {
        Student temp = x;
        x = y;
        y = temp;
        System.out.println("x:" + x.getName());
//        System.out.println("x:"+x);
//        System.out.println("y:"+y);
        System.out.println("y:" + y.getName());
    }
}
