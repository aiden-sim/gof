package creational.builder.builder;

import creational.common.Direction;
import creational.common.production.Door;
import creational.common.production.Maze;
import creational.common.production.Room;
import creational.common.production.Wall;

import static creational.common.Direction.*;

public class StandardMazeBuilder extends MazeBuilder {
    private Maze currentMaze;


    @Override
    public void buildMaze() {
        currentMaze = new Maze();
    }

    @Override
    public void buildRoom(int n) {
        if (currentMaze.roomList.get(n) == null) {
            Room room = new Room(n);
            room.setSide(NORTH, new Wall());
            room.setSide(EAST, new Wall());
            room.setSide(SOUTH, new Wall());
            room.setSide(WEST, new Wall());

            currentMaze.addRoom(room);
        }
    }

    @Override
    public void buildDoor(int roomFrom, int roomTo) {
        Room r1 = currentMaze.roomList.get(roomFrom);
        Room r2 = currentMaze.roomList.get(roomTo);
        Door d = new Door(r1, r2);

        r1.setSide(commonWall(r1, r2), d);
        r2.setSide(commonWall(r2, r1), d);
    }

    @Override
    public Maze getMaze() {
        return currentMaze;
    }

    private Direction commonWall(Room r1, Room r2) {
        if (r2.getRoomNumber() == r1.getRoomNumber() + 1) {
            return Direction.EAST;
        }
        if (r1.getRoomNumber() == r2.getRoomNumber() + 1) {
            return Direction.WEST;
        }
        if (r2.getRoomNumber() > r1.getRoomNumber()) {
            return Direction.SOUTH;
        }
        if (r1.getRoomNumber() > r2.getRoomNumber()) {
            return Direction.NORTH;
        }
        return null;
    }
}
