package behavioral.iterator;

interface Iterator<T> {
    void first();

    void next();

    boolean isDone();

    T currentItem();
}
