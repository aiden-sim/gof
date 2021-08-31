package creational.singleton.example2.case1;

public class Client {
    public static void main(String[] args) {
        // Client code to create factory the first time
        MazeFactory factory = MazeFactory.instance("enchanted");

        // Client code to access the factory
        MazeFactory factory2 = MazeFactory.instance();
    }
}
