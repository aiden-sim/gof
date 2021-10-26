package behavioral.iterator;

public interface Traversable<T> {
    Iterator<T> createIterator();
}