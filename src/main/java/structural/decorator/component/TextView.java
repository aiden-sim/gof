package structural.decorator.component;

/**
 * concrete component
 */
public class TextView implements VisualComponent {
    @Override
    public void draw() {
        System.out.println("Drawing text view");
    }

    @Override
    public void resize() {
        System.out.println("Resizing text view");
    }
}