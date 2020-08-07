package com.lcl.test;

import java.util.Arrays;

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

    public static void main(String[] args) {
        int i = Runtime.getRuntime().availableProcessors();
        System.err.println(i);
//        String phones = "106083560，106083998，106085567，106087227，106087534，106088220，106088702，106089003，106090152，106090458，106090924，106091518，106092824，106093128，106093377，106093618，106093991，106094314，106094569，106094861，106095092，106095320，106095562，106095846，106096108，106096433，58353398，51885187";
//        String[] phoneArr = phones.split("，");
//        for (String s : phoneArr) {
//            System.err.println(s);
//        }

    }


    private static long modLong(long temp) {
        return temp % 10;
    }

}