package behavioral.command.commands;

import java.util.ArrayList;
import java.util.List;

/**
 * ConcreteCommand
 */
public class MacroCommand implements Command {
    private List<Command> commands;

    public MacroCommand() {
        commands = new ArrayList<>();
    }

    public void add(Command command) {
        commands.add(command);
    }

    public void remove(Command command) {
        commands.remove(command);
    }

    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }
}