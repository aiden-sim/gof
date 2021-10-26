package behavioral.iterator.example;

/**
 * Iterator(반복자)
 * 요소를 순서대로 검색해가는 인터페이스를 결정
 */
public interface Iterator {
    boolean hasNext();

    Object next();
}
