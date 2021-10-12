package structural.flyweight.example;

public class AllState {
    private String state;

    public AllState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }
}