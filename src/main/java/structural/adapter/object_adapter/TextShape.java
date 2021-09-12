package structural.adapter.object_adapter;

import structural.adapter.*;

/**
 * adapter (합성)
 */
public class TextShape implements Shape {
    private TextView textView;
    private Coord bottom, left, width, height;

    public TextShape(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void boundingBox(Point bottomLeft, Point topRight) {
        textView.getOrigin(bottom.value, left.value);
        textView.getExtent(width.value, height.value);

        bottomLeft = new Point(bottom.value, left.value);
        topRight = new Point(bottom.value + height.value, left.value + width.value);
    }

    public boolean isEmpty() {
        return textView.isEmpty();
    }

    @Override
    public Manipulator createManipulator() {
        return new TextManipulator(this);
    }
}
