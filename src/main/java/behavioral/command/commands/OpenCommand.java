package behavioral.command.commands;

import behavioral.command.Application;
import behavioral.command.Document;

/**
 * ConcreteCommand
 */
public class OpenCommand implements Command {
    private Application application;

    public OpenCommand(Application application) {
        this.application = application;
    }

    @Override
    public void execute() {
        String name = askUser();
        if (name != null) {
            Document document = new Document(name);
            application.add(document);
            document.open();
        }
    }

    protected String askUser() {
        System.out.println("Asking user for document name");
        return "someDocName";
    }
}