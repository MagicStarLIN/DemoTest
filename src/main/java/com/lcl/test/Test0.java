package com.lcl.test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: nono
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-08-27 16:51
 */
public class Test0 {
    public static void main(String[] args) {
        Student s1 = new Student("lcl1", "1", 21);
        Student s2 = new Student("lcl2", "1", 21);
        Student s3 = new Student("lcl3", "2", 21);
        Student s4 = new Student("lcl4", "3", 21);
        Student s5 = new Student("lcl5", "4", 21);
        Student s6 = new Student("lcl6", "5", 21);
        Student s7 = new Student("lcl7", "5", 21);
        Student s8 = new Student("lcl8", "6", 21);
        List<Student> arr = new ArrayList<>();
        HashSet<Student> set = new HashSet<>();
        arr.add(s1);
        arr.add(s2);
        arr.add(s3);
        arr.add(s4);
        arr.add(s5);
        arr.add(s6);
        arr.add(s7);
        arr.add(s8);
//        System.out.println(arr.size());
//        set.addAll(arr);
//        System.out.println(set.size());

        List<Student> students = arr.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() ->
                                new TreeSet<>(Comparator.comparing(Student::getId))), ArrayList::new)
        );
        for (Student student : students) {
            System.out.println(student.toString());

        }
    }

}
