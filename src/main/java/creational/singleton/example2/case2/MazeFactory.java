package creational.singleton.example2.case2;

public abstract class MazeFactory {
    protected static MazeFactory uniqueInstance = null;

    protected MazeFactory() {
    }

    public static MazeFactory instance() {
        return uniqueInstance;
    }
}
