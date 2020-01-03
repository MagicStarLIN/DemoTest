package com.lcl.test;

import com.alibaba.excel.EasyExcel;

import java.io.IOException;
import java.sql.Timestamp;
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

    private static void testMethod2() {
        Student student = new Student("lcl", "1", 0);
        changeStudent(student);
        System.err.println(student.toString());
    }

    private static void changeStudent(Student student) {
         student = new Student("lsy", "34", 0);
    }

}