package structural.flyweight.example;

public class IntrinsicState {
    private String state;

    public IntrinsicState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }
}
