package creational.factory_method.creator;

import creational.abstract_factory.product.BombedWall;
import creational.abstract_factory.product.RoomWithABomb;
import creational.common.product.Door;
import creational.common.product.Maze;
import creational.common.product.Room;
import creational.common.product.Wall;

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
