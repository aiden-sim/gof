package behavioral.mediator.example;

public abstract class Colleague {
    private Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    protected void notifyMediator() {
        mediator.handleOperation(this);
    }

    public abstract void operation();
}