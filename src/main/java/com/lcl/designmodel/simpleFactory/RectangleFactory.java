package com.lcl.designmodel.simpleFactory;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: RectangleFactory
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-08-23 15:13
 */
public class RectangleFactory implements Factory {
    @Override
    public Shape getShape() {
        return new Rectangle();
    }
}
