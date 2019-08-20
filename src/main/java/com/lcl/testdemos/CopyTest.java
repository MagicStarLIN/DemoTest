package com.lcl.testdemos;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: CopyTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-08-13 14:05
 */
public class CopyTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address address = new Address("丽水路","唐山");
        Person person = new Person(21,"lcl",address);
        Person clonePerson = (Person) person.clone();

        address.setPlace("beijing");
        System.out.println(person);
        System.out.println(clonePerson);
        System.out.println(person);

    }
}
