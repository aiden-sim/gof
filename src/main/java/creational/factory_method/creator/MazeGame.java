package creational.factory_method.creator;

import creational.common.product.Door;
import creational.common.product.Maze;
import creational.common.product.Room;
import creational.common.product.Wall;

import static creational.common.Direction.*;

/**
 * Creator
 */
public abstract class MazeGame {

    /**
     * Factory Method
     */
    public abstract Maze makeMaze();

    public abstract Room makeRoom(int n);

    public abstract Wall makeWall();

    public abstract Door makeDoor(Room r1, Room r2);

    public Maze createMaze() {
        Maze maze = makeMaze();
        Room r1 = makeRoom(1);          // 팩토리 메서드 사용
        Room r2 = makeRoom(2);          // 팩토리 메서드 사용
        Door theDoor = makeDoor(r1, r2);   // 팩토리 메서드 사용

        r1.setSide(NORTH, makeWall());
        r1.setSide(EAST, theDoor);
        r1.setSide(SOUTH, makeWall());
        r1.setSide(WEST, makeWall());

        r2.setSide(NORTH, makeWall());
        r2.setSide(EAST, makeWall());
        r2.setSide(SOUTH, makeWall());
        r2.setSide(WEST, theDoor);

        maze.addRoom(r1);
        maze.addRoom(r2);
        return maze;
    }

    public static void main(String[] args) {

    }
}
