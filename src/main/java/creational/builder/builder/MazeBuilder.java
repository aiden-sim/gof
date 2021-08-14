package creational.builder.builder;

import creational.common.production.Maze;

/**
 * Builder
 */
public abstract class MazeBuilder {
    public abstract void buildMaze();

    public abstract void buildRoom(int room);

    public abstract void buildDoor(int roomFrom, int roomTo);

    public abstract Maze getMaze(); // getResult
}
