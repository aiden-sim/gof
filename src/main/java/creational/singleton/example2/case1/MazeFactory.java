package creational.singleton.example2.case1;

/**
 * Singleton With Subclassing Method 1
 */
public abstract class MazeFactory {
    private static MazeFactory uniqueInstance = null;

    protected MazeFactory() {

    }

    public static MazeFactory instance() {
        if (uniqueInstance == null) return instance("enchanted");
        else return uniqueInstance;
    }

    // Create the instance using the specified String name.
    public static MazeFactory instance(String name) {
        if (uniqueInstance == null)
            if (name.equals("enchanted"))
                uniqueInstance = new EnchantedMazeFactory();
            else if (name.equals("agent"))
                uniqueInstance = new AgentMazeFactory();
        return uniqueInstance;
    }

}
