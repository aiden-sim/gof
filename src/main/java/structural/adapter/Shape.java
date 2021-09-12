package structural.adapter;

/**
 * Target
 */
public interface Shape {
    void boundingBox(Point bottomLeft, Point topRight);

    Manipulator createManipulator();
}
