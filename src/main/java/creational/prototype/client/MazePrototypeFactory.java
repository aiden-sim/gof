package creational.prototype.client;

import creational.common.product.Door;
import creational.common.product.Maze;
import creational.common.product.Room;
import creational.common.product.Wall;

/**
 * Client
 * 생성할 객체의 원형(인터페이스)로 초기화됨
 */
public class MazePrototypeFactory implements MazeFactory {
    Maze prototypeMaze;
    Wall prototypeWall;
    Room prototypeRoom;
    Door prototypeDoor;

    public MazePrototypeFactory(Maze maze, Wall wall, Room room, Door door) {
        prototypeMaze = maze;
        prototypeWall = wall;
        prototypeRoom = room;
        prototypeDoor = door;
    }

    @Override
    public Maze makeMaze() {
        return prototypeMaze.clone();
    }

    @Override
    public Wall makeWall() {
        return prototypeWall.clone();
    }

    @Override
    public Room makeRoom(int n) {
        return prototypeRoom.clone();
    }

    @Override
    public Door makeDoor(Room r1, Room r2) {
        Door door = prototypeDoor.clone();
        door.initialize(r1, r2);
        return door;
    }
}
