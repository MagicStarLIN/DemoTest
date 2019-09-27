package com.lcl.test;

import lombok.Data;

import java.util.Objects;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Student
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-08-06 18:00
 */
@Data
public class Student {
    private String name;

    private String id;

    private int sex;

    public Student() {

    }

    public Student(String name, String id, int sex) {
        this.name = name;
        this.id = id;
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(getId(), student.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
