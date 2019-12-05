package com.lcl.testdemos;

import lombok.Data;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Person
 * @date 2019-08-13 14:08
 */
@Data
public class Person implements Cloneable {
    private int age;
    private String name;
    private Address address;

    public Person(int age, String name, Address address) {
        this.age = age;
        this.name = name;
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
