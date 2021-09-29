package structural.composite.component;

import javax.naming.OperationNotSupportedException;
import java.util.Iterator;

/**
 * component
 */
public abstract class Equipment implements Iterable<Equipment> {
    private String name;

    protected Equipment(String name) {
        this.name = name;
    }

    public abstract int power();

    public abstract float netPrice();

    public abstract float discountPrice();

    public String getName() {
        return name;
    }

    public void add(Equipment equipment) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    public void remove(Equipment equipment) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    @Override
    public Iterator<Equipment> iterator() {
        return new Iterator<Equipment>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Equipment next() {
                return null;
            }

            @Override
            public void remove() {
            }
        };
    }
}