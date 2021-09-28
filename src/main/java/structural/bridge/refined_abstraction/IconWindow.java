package structural.bridge.refined_abstraction;

import structural.View;
import structural.bridge.abstraction.Window;
import structural.bridge.implementor.WindowImp;

public class IconWindow extends Window {
    private String bitmapName;

    public IconWindow(View contents) {
        super(contents);
    }

    @Override
    public void drawContents() {
        WindowImp imp = getWindowImp();
        if (imp != null) {
            imp.deviceBitmap(bitmapName, 0.0f, 0.0f);
        }
    }

    public String getBitmapName() {
        return bitmapName;
    }

    public void setBitmapName(String iconName) {
        this.bitmapName = iconName;
    }
}
