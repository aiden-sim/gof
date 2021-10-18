package behavioral.command.example;

public class LampOffCommand implements Command {
    private Lamp lamp;

    public LampOffCommand(Lamp lamp) {
        this.lamp = lamp;
    }

    public void execute() {
        lamp.turnOff();
    }
}
