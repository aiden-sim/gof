package behavioral.iterator.example;

/**
 * ConcreteIterator(구체적인 반복자)
 * Iterator가 결정한 인터페이스를 실제로 구현
 */
public class BookShelfIfIterator implements Iterator {
    private BookShelf bookShelf;
    private int index;

    public BookShelfIfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.index = 0;
    }

    public boolean hasNext() {
        if (index < bookShelf.getLength()) {
            return true;
        }
        return false;
    }

    public Object next() {
        Book book = bookShelf.getBookAt(index);
        index++;
        return book;
    }
}