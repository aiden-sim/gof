package creational.prototype.client;

import creational.common.product.Door;
import creational.common.product.Maze;
import creational.common.product.Room;
import creational.common.product.Wall;
import creational.prototype.prototype.BombedWall;
import creational.prototype.prototype.RoomWithABomb;

import static creational.common.Direction.*;

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
        Maze maze = new Maze();
        Wall wall = new Wall();
        Room room = new Room();
        Door door = new Door();

        MazeFactory simpleMazeFactory = new MazePrototypeFactory(maze, wall, room, door);
        maze = game.createMaze(simpleMazeFactory);

        // 다른 미로 형식
        // abstract 에서는 BombedMazeFactory,  EnchantedMazeFactory(ConcreteFactory)를 별도로 만들었다.
        Room roomWithABomb = new RoomWithABomb();
        Wall bombedWall = new BombedWall();
        MazeFactory bombedMazeFactory = new MazePrototypeFactory(maze, bombedWall, roomWithABomb, door);
        maze = game.createMaze(bombedMazeFactory);
    }
}
