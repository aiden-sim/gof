package structural.decorator.decorator;

import structural.decorator.component.VisualComponent;

/**
 * decorator
 */
public class Decorator implements VisualComponent {
    private VisualComponent component;

    public Decorator(VisualComponent component) {
        this.component = component;
    }

    @Override
    public void draw() {
        component.draw();
    }

    @Override
    public void resize() {
        component.resize();
    }
}
