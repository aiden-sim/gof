package behavioral.command;

import behavioral.command.commands.Command;

/**
 * Invoker
 */
public class MenuItem {
    private Command command;

    public void storeCommand(Command command) {
        this.command = command;
    }

    public void invokeCommand() {
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Command is not configured");
        }
    }
}
