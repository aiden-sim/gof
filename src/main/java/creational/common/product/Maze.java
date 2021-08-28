package creational.common.product;

import java.util.ArrayList;
import java.util.List;

/**
 * 방들의 집합을 표현
 * AbstractProduct
 */
public class Maze implements Cloneable {
    public List<Room> roomList;

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public Maze() {
        roomList = new ArrayList<Room>();
    }

    public void addRoom(Room r) {
        roomList.add(r);
    }

    /**
     * Clone operation
     */
    @Override
    public Maze clone() {
        Maze cloned;
        try {
            cloned = (Maze) super.clone();
        } catch (CloneNotSupportedException e) {
            cloned = new Maze();
        }
        cloned.roomList = (List<Room>) (((ArrayList<Room>) roomList).clone());
        return cloned;
    }
}
