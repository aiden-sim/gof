package creational.abstract_factory.production;

import java.util.ArrayList;
import java.util.List;

/**
 * 방들의 집합을 표현
 * AbstractProduct
 */
public class Maze {
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

    public Maze clone() {
        return null;
    }
}
