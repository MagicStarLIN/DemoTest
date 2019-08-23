package com.lcl.designmodel.simpleFactory;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Rectangle
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-08-23 14:06
 */
public class Rectangle implements Shape {
    public Rectangle() {
        System.out.println("a Rectangle create");
    }

    @Override
    public void draw() {
        System.out.println("a Rectangle draw");
    }
}
