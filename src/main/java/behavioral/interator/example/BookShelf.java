package behavioral.interator.example;

/**
 * ConcreteAggregate(구체적인 집합체)
 * Aggregate 역할이 결정한 인터페이스를 실제로 구현하는 일
 */
public class BookShelf implements Aggregate {
    private Book[] books;
    private int last = 0;

    public BookShelf(int maxsize) {
        this.books = new Book[maxsize];
    }

    public Book getBookAt(int index) {
        return books[index];
    }

    public void appendBook(Book book) {
        this.books[last] = book;
        last++;
    }

    public int getLength() {
        return last;
    }

    public Iterator iterator() {
        return new BookShelfIfIterator(this);
    }
}