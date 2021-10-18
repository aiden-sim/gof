package behavioral.command.example;

public class Client {
    public static void main(String[] args) {
        Lamp lamp = new Lamp();
        Command lampOnCommand = new LampOnCommand(lamp);
        Command lampOffCommand = new LampOffCommand(lamp);
        Button button = new Button(lampOnCommand);
        button.pressed();
        button.setCommand(lampOffCommand);
        button.pressed();
    }
}
