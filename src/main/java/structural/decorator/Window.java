package structural.decorator;

import structural.decorator.component.VisualComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * composite 역할과 비슷
 */
public class Window {
    private List<VisualComponent> contents;

    public Window() {
        contents = new ArrayList<>();
    }

    public void addComponent(VisualComponent component) {
        this.contents.add(component);
    }

    public void draw() {
        for (int i = 0; i < contents.size(); i++) {
            System.out.println("*** Component " + (i + 1) + " ***");
            contents.get(i).draw();
        }
    }
}
