package structural.bridge;

import structural.bridge.concrete_implementor.XWindowImp;
import structural.bridge.implementor.WindowImp;

public class WindowSystemFactory {
    private static WindowSystemFactory instance = null;

    private WindowSystemFactory() {
    }

    public WindowImp makeWindowImp() {
        return new XWindowImp();
    }

    public static WindowSystemFactory getInstance() {
        if (instance == null) {
            instance = new WindowSystemFactory();
        }
        return instance;
    }
}
