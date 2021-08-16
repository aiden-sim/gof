package creational.abstract_factory.client;

import creational.abstract_factory.factory.BombedMazeFactory;
import creational.abstract_factory.factory.EnchantedMazeFactory;
import creational.abstract_factory.factory.MazeFactory;
import creational.common.product.Door;
import creational.common.product.Maze;
import creational.common.product.Room;

import static creational.common.Direction.*;

/**
 * Client
 */
public class MazeGame {
    public Maze createMaze(MazeFactory factory) {
        Maze maze = factory.makeMaze();
        Room r1 = factory.makeRoom(1);
        Room r2 = factory.makeRoom(2);
        Door door = factory.makeDoor(r1, r2);

        maze.addRoom(r1);
        maze.addRoom(r2);

        r1.setSide(NORTH, factory.makeWall());
        r1.setSide(EAST, door);
        r1.setSide(SOUTH, factory.makeWall());
        r1.setSide(WEST, factory.makeWall());

        r2.setSide(NORTH, factory.makeWall());
        r2.setSide(EAST, factory.makeWall());
        r2.setSide(SOUTH, factory.makeWall());
        r2.setSide(WEST, door);

        return maze;
    }

    public static void main(String[] args) {
        MazeGame game = new MazeGame();
        MazeFactory factory1 = new BombedMazeFactory(); // 폭탄이 설치되어 있는 미로
        Maze maze1 = game.createMaze(factory1);
        maze1.getRoomList();

        MazeFactory factory2 = new EnchantedMazeFactory(); // 마법에 걸린 미로
        Maze maze2 = game.createMaze(factory2);
        maze2.getRoomList();
    }
}
