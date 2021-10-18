package behavioral.chain_of_responsibility;

public class Application extends HelpHandler {
    public Application(Topic topic) {
        super(null, topic);
    }

    @Override
    public void handleHelp() {
        System.out.println("Application: handling help request");
    }
}