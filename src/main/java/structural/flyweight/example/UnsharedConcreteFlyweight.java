package structural.flyweight.example;

public class UnsharedConcreteFlyweight implements Flyweight {
    private AllState allState;

    public UnsharedConcreteFlyweight(AllState allState) {
        System.out.println("Creating unshared concrete flyweight with all state = " + allState);
        this.allState = allState;
    }

    @Override
    public void operation(ExtrinsicState state) {
        System.out.println("UnsharedConcreteFlyweight.operation(" + state + "): all state is " + allState);
    }
}