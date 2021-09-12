package structural.adapter;

/**
 * adaptee
 */
public class TextView {
    private float x;
    private float y;
    private float width;
    private float height;

    public TextView() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.width = 0.0f;
        this.height = 0.0f;
    }

    public TextView(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Point getOrigin(int x, int y) {
        return new Point(x, y);
    }

    public Point getExtent(int width, int height) {
        return new Point(width, height);
    }

    public boolean isEmpty() {
        return (width == 0.0f) || (height == 0.0f);
    }
}
