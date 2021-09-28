package structural.bridge.refined_abstraction;

import structural.View;
import structural.bridge.abstraction.Window;

public class ApplicationWindow extends Window {
    public ApplicationWindow(View contents) {
        super(contents);
    }

    @Override
    public void drawContents() {
        getView().drawOn(this);
    }
}
