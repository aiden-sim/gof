package structural.bridge.implementor;

import structural.Point;

/**
 * implementor
 */
public interface WindowImp {
    void impTop();

    void impBottom();

    void impSetExtent(Point extent);

    void impSetOrigin(Point origin);

    void deviceRect(float x, float y, float w, float h);

    void deviceText(String text, Point at);

    void deviceBitmap(String text, float x, float y);
}
