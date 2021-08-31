package creational.singleton.example1;

import java.util.HashMap;
import java.util.Map;

/**
 * 단일체에 대한 레지스트리 사용
 */
public class Singleton {
    private static final Map<String, Singleton> REGISTRY = new HashMap<>();

    private static Singleton instance;

    protected Singleton() {
        register("SINGLETON", this);
    }

    protected static Singleton lookUp(String key) {
        return REGISTRY.get(key);
    }

    public static Singleton getInstance() {
        if (instance == null) {
            final String singletonName = "SINGLETON";

            instance = lookUp(singletonName);
        }
        return instance;
    }

    public static void register(String key, Singleton instance) {
        REGISTRY.put(key, instance);
    }
}
