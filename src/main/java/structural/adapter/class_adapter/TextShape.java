package structural.adapter.class_adapter;

import structural.adapter.*;

/**
 * adapter (클래스)
 */
public class TextShape extends TextView implements Shape {
    Coord bottom, left, width, height;

    @Override
    public void boundingBox(Point bottomLeft, Point topRight) {
        getOrigin(bottom.value, left.value);
        getExtent(width.value, height.value);

        bottomLeft = new Point(bottom.value, left.value);
        topRight = new Point(bottom.value + height.value, left.value + width.value);
    }


    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public Manipulator createManipulator() {
        return new TextManipulator(this);
    }
}
