package behavioral.command.commands;

import behavioral.command.Document;

/**
 * ConcreteCommand
 */
public class CutCommand implements Command {
    private Document document;

    public CutCommand(Document document) {
        this.document = document;
    }

    @Override
    public void execute() {
        document.cut();
    }
}