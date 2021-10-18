package behavioral.command.commands;

import behavioral.command.Command;
import behavioral.command.Document;

public class PasteCommand implements Command {
    private Document document;

    public PasteCommand(Document document) {
        this.document = document;
    }

    @Override
    public void execute() {
        document.paste();
    }
}