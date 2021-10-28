package behavioral.mediator;

/**
 * ConcreteMediator
 */
public class FontDialogDirector extends DialogDirector {
    private Button ok;
    private Button cancel;
    private ListBox fontList;
    private EntryField fontName;

    @Override
    protected void createWidgets() {
        this.ok = new Button(this);
        this.cancel = new Button(this);
        this.fontList = new ListBox(this);
        this.fontName = new EntryField(this);
    }

    @Override
    public void showDialog() {
        System.out.println("FontDialogDirector: showing dialog");
    }

    @Override
    public void widgetChanged(Widget changedWidget) {
        if (changedWidget == fontList) {
            System.out.println("Setting fontName.text = fontList.getSelection()");
            fontName.setText(fontList.getSelection());
        } else if (changedWidget == ok) {
            System.out.println("Modifying font");
        } else if (changedWidget == cancel) {
            System.out.println("Closing the dialog");
        }
    }

    public static void pressOk(FontDialogDirector director) {
        director.ok.handleMouse(new MouseEvent());
    }
}