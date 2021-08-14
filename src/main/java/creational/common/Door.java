package creational.common;

/**
 * 방의 각 측면에 있을 수 있는 문
 */
public class Door extends MapSite {
    private Room room1;
    private Room room2;
    private boolean isOpen;

    // 어느 방 사이에 있는지 알아야됨
    public Door(Room room1, Room room2) {
        this.room1 = room1;
        this.room2 = room2;
    }

    public Room otherSideFrom(Room room) {
        return room1.equals(room) ? room1 : room2;
    }

    @Override
    public void enter() {

    }
}
