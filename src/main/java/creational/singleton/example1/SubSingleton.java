package creational.singleton.example1;

public class SubSingleton extends Singleton {
    private static SubSingleton instance;

    protected SubSingleton() {
        register("SUBSINGLETON", this);
    }

    public static Singleton getInstance() {
        return lookUp("SUBSINGLETON");
    }
}
