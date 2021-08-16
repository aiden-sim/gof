package creational.builder.builder;

import creational.common.product.Maze;

/**
 * ConcreteBuilder
 */
public class CountingMazeBuilder extends MazeBuilder {
    private int doors;
    private int rooms;

    public CountingMazeBuilder() {
        doors = 0;
        rooms = 0;
    }

    @Override
    public void buildMaze() {

    }

    @Override
    public void buildRoom(int room) {
        room++;
    }

    @Override
    public void buildDoor(int roomFrom, int roomTo) {
        doors++;
    }

    @Override
    public Maze getMaze() {
        return null;
    }

    public int getRooms() {
        return rooms;
    }

    public int getDoors() {
        return doors;
    }
}
