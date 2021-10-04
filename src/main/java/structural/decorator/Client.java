package structural.decorator;

import structural.decorator.component.TextView;
import structural.decorator.decorator.BorderDecorator;
import structural.decorator.decorator.ScrollDecorator;

public class Client {
    public static void main(String[] args) {
        Window window = new Window();

        window.addComponent(new BorderDecorator(new ScrollDecorator(new TextView()), 30));

        window.addComponent(new ScrollDecorator(new TextView()));

        window.draw();
    }
}
