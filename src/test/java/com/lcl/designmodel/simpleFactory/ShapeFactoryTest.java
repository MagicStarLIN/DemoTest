package com.lcl.designmodel.simpleFactory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;

class ShapeFactoryTest {

    @Test
    void returnsNullForASeparatelyAllocatedEmptyShapeType() {
        assertNull(ShapeFactory.getShape(new String("")));
    }

    @Test
    void returnsNullForASeparatelyAllocatedBlankShapeType() {
        assertNull(ShapeFactory.getShape(new String(" ")));
    }

    @Test
    void createsACircleForTheCircleShapeType() {
        assertInstanceOf(Circle.class, ShapeFactory.getShape("circle"));
    }
}
