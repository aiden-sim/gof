package creational.singleton.example2.case2;

public class Client {
    public static void main(String[] args) {
        // Client code to create factory the first time:
        MazeFactory factory = EnchantedMazeFactory.instance();
        // Client code to access the factory
        MazeFactory factory2 = MazeFactory.instance();
    }
}
