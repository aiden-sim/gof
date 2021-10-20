package behavioral.command.commands;

import behavioral.command.Application;
import behavioral.command.Document;

/**
 * ConcreteCommand
 */
public class CloseCommand implements Command {
    private Application application;

    public CloseCommand(Application application) {
        this.application = application;
    }

    @Override
    public void execute() {
        Document currentDocument = application.getCurrentDocument();
        if (currentDocument != null) {
            application.remove(currentDocument);
            currentDocument.close();
        }
    }
}