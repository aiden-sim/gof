package creational.factory_method.creator;

import creational.abstract_factory.production.BombedWall;
import creational.abstract_factory.production.RoomWithABomb;
import creational.common.production.Door;
import creational.common.production.Maze;
import creational.common.production.Room;
import creational.common.production.Wall;

public class BombedMazeGame extends MazeGame {

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
