package structural.flyweight.example;

public class ConcreteFlyweight implements Flyweight {
    private IntrinsicState intrinsicState;

    public ConcreteFlyweight(IntrinsicState intrinsicState) {
        System.out.println("Creating concrete flyweight with internal state = " + intrinsicState);
        this.intrinsicState = intrinsicState;
    }

    @Override
    public void operation(ExtrinsicState state) {
        System.out.println("ConcreteFlyweight.operation(" + state + "): internal state is " + intrinsicState);
    }
}