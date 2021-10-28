package behavioral.mediator;

/**
 * ConcreteColleague
 */
public class ListBox extends Widget {
    public ListBox(DialogDirector director) {
        super(director);
    }

    public String getSelection() {
        return null;
    }

    @Override
    public void handleMouse(MouseEvent event) {
        super.handleMouse(event);
    }
}