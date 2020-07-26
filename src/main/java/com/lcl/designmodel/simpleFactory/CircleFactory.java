package com.lcl.designmodel.simpleFactory;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: CircleFactory
 * @date 2019-08-23 15:12
 */
public class CircleFactory implements Factory {
    @Override
    public Shape getShape() {
        return new Circle();
    }
}
