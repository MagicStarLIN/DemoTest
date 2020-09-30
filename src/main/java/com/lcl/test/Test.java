package com.lcl.test;

import java.util.Arrays;
import java.util.Date;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test
 * @date 2019/11/14 8:42 下午
 */
public class Test {
    volatile String lcl = "lcl";
    private static void testMethod() {
         int i = 1;
        int[] arr = {1, 2, 3, 4};
//        changeArr(arr);
        System.err.println(Arrays.toString(arr));
    }
    private static void changeArr(int i) {
//        arr = {4, 3, 2, 1};
        i = 2;
    }
//
//    private static void testMethod2() {
//        Student student = new Student("lcl", "1", 0);
//        changeStudent();
//        System.err.println(student.toString());
//    }

    private static String changeStudent(String str) {
        String s = "您的视频简历" + str + "已经发送给boss 播放视频附件";
        return s.substring(16 + str.length());
    }

    public static void main(String[] args) {
        String s = changeStudent("刘常林");
        System.err.println(s);
        System.err.println(s.length());

//        System.err.println(compareDate());

    }

    private static int compareDate(Date d1, Date d2) {
        long dif = d1.getTime() - d2.getTime();

        return Integer.parseInt(String.valueOf(dif));
    }

    private static long modLong(long temp) {
        return temp % 10;
    }

}