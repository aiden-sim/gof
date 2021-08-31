package creational.singleton.example2.case2;

public class EnchantedMazeFactory extends MazeFactory {
    public static MazeFactory instance() {
        if (uniqueInstance == null)
            uniqueInstance = new EnchantedMazeFactory();
        return uniqueInstance;
    }

    private EnchantedMazeFactory() {
    }
}
