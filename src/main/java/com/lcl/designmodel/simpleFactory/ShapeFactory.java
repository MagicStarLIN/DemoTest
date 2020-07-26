package com.lcl.designmodel.simpleFactory;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: ShapeFactory
 * @date 2019-08-23 14:08
 */
public class ShapeFactory {
    public static Shape getShape(String shapeType) {
        if (shapeType == null || shapeType == "") {
            return null;
        }
        if (shapeType.equalsIgnoreCase("circle")) {
            return new Circle();
        }
        if (shapeType.equalsIgnoreCase("rectangle")) {
            return new Rectangle();
        }
        return null;
    }
}
