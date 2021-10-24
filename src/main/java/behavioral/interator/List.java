package behavioral.interator;

public interface List<T> extends Traversable<T> {
    long count();

    T get(long index);

    T first();

    T last();

    boolean includes(T item);

    void append(T item);

    void prepend(T item);

    void remove(T item);

    void removeLast();

    void removeFirst();

    void removeAll();

    T top();

    void push(T item);

    T pop();
}