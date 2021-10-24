package behavioral.interator;

public interface Traversable<T> {
    Iterator<T> createIterator();
}