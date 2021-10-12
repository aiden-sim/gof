package structural.flyweight.example;

public class ExtrinsicState {
    private String state;

    public ExtrinsicState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }
}