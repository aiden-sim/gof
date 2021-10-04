package structural.decorator.decorator;

import structural.decorator.component.VisualComponent;

/**
 * concrete decorator
 */
public class BorderDecorator extends Decorator {
    private int width;

    public BorderDecorator(VisualComponent component, int borderWidth) {
        super(component);
        this.width = borderWidth;
    }

    @Override
    public void draw() {
        super.draw();
        drawBorder(width);
    }

    /**
     * 외부에서 알 수 없도록 하기 위해
     */
    private void drawBorder(int w) {
        System.out.println("Drawing border with width=" + w);
    }
}
