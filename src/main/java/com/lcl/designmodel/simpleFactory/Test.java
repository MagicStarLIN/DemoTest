package com.lcl.designmodel.simpleFactory;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test
 * @date 2019-08-23 14:11
 */
public class Test {
    public static void main(String[] args) {
        Shape s = ShapeFactory.getShape("circle");
        s.draw();
    }
}
