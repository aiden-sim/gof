package behavioral.interpreter.example;

public class Context {
    private String value;

    public Context(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
