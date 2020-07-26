package com.lcl.designmodel.simpleFactory;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Circle
 * @date 2019-08-23 14:01
 */
public class Circle implements Shape {
    public Circle() {
        System.out.println("a circle create");
    }
    @Override

    public void draw() {
        System.out.println("a circle draw");
    }
}
