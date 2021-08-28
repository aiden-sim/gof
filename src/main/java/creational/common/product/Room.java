package creational.common.product;

import creational.abstract_factory.product.MapSite;
import creational.common.Direction;

import java.util.EnumMap;
import java.util.Map;

/**
 * 미로에 있는 다른 요소와 관련성을 갖도록 정의
 * AbstractProduct
 */
public class Room extends MapSite implements Cloneable {
    private int roomNumber;
    private Map<Direction, MapSite> sides = new EnumMap<>(Direction.class);

    public Room() {

    }

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public MapSite getSide(Direction direction) {
        return sides.get(direction);
    }

    public void setSide(Direction direction, MapSite mapSite) {
        sides.put(direction, mapSite);
    }

    @Override
    public void enter() {

    }

    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Clone operation
     */
    @Override
    public Room clone() {
        try {
            Room cloned = (Room) super.clone();
            cloned.sides = new EnumMap<>(sides);
            return cloned;
        } catch (CloneNotSupportedException e) {
            return new Room(this.roomNumber);
        }
    }
}
