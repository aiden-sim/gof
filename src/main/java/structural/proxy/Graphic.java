package structural.proxy;

import structural.Point;

import java.io.InputStream;
import java.io.OutputStream;

public interface Graphic {
    void draw(Point at);

    void handleMouse(Event event);

    Point getExtent();

    void load(InputStream inputStream);

    void save(OutputStream outputStream);
}
