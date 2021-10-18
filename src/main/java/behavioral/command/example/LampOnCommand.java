package behavioral.command.example;

public class LampOnCommand implements Command {
    private Lamp lamp;

    public LampOnCommand(Lamp lamp) {
        this.lamp = lamp;
    }

    public void execute() {
        lamp.turnOn();
    }
}