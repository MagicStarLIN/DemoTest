package com.lcl.test;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test5
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-08-06 17:59
 */
public class Test5 {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Student s1 = new Student("小张");
        Student s2 = new Student("小李");
        Test5.swap(s1, s2);
        System.out.println("s1:" + s1.getName());
//        System.out.println("s1:" + s1);
        System.out.println("s2:" + s2.getName());
//        System.out.println("s2:" + s2);
    }

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
