package creational.abstract_factory.factory;

import creational.abstract_factory.production.Door;
import creational.abstract_factory.production.Maze;
import creational.abstract_factory.production.Room;
import creational.abstract_factory.production.Wall;

/**
 * AbstractFactory
 */
public interface MazeFactory {
    Maze makeMaze();

    Wall makeWall();

    Room makeRoom(int n);

    Door makeDoor(Room r1, Room r2);
}
