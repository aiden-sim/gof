package creational.common.product;

import creational.abstract_factory.product.MapSite;

/**
 * 방의 각 측면에 있을 수 있는 문
 * AbstractProduct
 */
public class Door extends MapSite implements Cloneable {
    private Room room1;
    private Room room2;
    private boolean isOpen;

    public Door() {

    }

    // 어느 방 사이에 있는지 알아야됨
    public Door(Room room1, Room room2) {
        this.room1 = room1;
        this.room2 = room2;
    }

    // 복사 생성자 추가
    public Door(Door other) {
        room1 = other.room1;
        room2 = other.room2;
    }

    public Room otherSideFrom(Room room) {
        return room1.equals(room) ? room1 : room2;
    }

    @Override
    public void enter() {

    }

    public void initialize(Room room1, Room room2) {
        this.room1 = room1;
        this.room2 = room2;
    }

    /**
     * Clone operation
     */
    @Override
    public Door clone() {
        try {
            return (Door) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Door(this);
        }
    }
}
