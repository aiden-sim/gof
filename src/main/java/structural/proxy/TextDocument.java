package structural.proxy;

public class TextDocument {
    public void insert(Graphic graphic) {
        graphic.save(System.out);
        System.out.println("Inserting to TextDocument graphic object with extent=" + graphic.getExtent());
    }
}
