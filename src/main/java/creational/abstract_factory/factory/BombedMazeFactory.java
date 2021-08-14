package creational.abstract_factory.factory;

import creational.abstract_factory.production.*;
import creational.common.production.Door;
import creational.common.production.Maze;
import creational.common.production.Room;
import creational.common.production.Wall;

/**
 * ConcreteFactory
 */
public class BombedMazeFactory implements MazeFactory {
    @Override
    public Maze makeMaze() {
        return new Maze();
    }

    @Override
    public Wall makeWall() {
        return new BombedWall();
    }

    @Override
    public Room makeRoom(int n) {
        return new RoomWithABomb(n);
    }

    @Override
    public Door makeDoor(Room r1, Room r2) {
        return new Door(r1, r2);
    }
}
