package creational.abstract_factory.factory;

import creational.common.production.Door;
import creational.common.production.Maze;
import creational.common.production.Room;
import creational.common.production.Wall;

/**
 * AbstractFactory
 */
public interface MazeFactory {
    Maze makeMaze();

    Wall makeWall();

    Room makeRoom(int n);

    Door makeDoor(Room r1, Room r2);
}
