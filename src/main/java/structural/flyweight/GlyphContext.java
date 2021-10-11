package structural.flyweight;

/**
 * 부가적 상태 (외적 상태)
 */
public class GlyphContext {
    @SuppressWarnings("unused")
    private int index;

    public void next(int step) {
        index += step;
    }

    public void next() {
        next(1);
    }

    public void insert(int quantity) {

    }

    public void insert() {
        next(1);
    }

    public Font getFont() {
        return null;
    }

    public void setFont(Font font, int span) {

    }

    public void setFont(Font font) {
        setFont(font, 1);
    }
}
