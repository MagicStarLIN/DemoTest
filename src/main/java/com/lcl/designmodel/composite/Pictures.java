package com.lcl.designmodel.composite;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class Pictures implements Graphics{

    private List<Graphics> composite = new ArrayList<>();


    @Override
    public void draw() {
        System.err.println("pictures draw_____");
        for (Graphics graphics : composite) {
            graphics.draw();
        }
        System.err.println("all done______");
    }

    public void add(Graphics graphics) {
        composite.add(graphics);
    }

    public void remove(Graphics graphics) {
        composite.remove(graphics);
    }

    public Graphics getChild(int i) {
        return composite.get(i);
    }

    public static void main(String[] args) {
        Circle circle = new Circle();
        Line line = new Line();
        Pictures pictures = new Pictures();
        pictures.add(circle);
        pictures.add(line);
        pictures.draw();
    }
}
