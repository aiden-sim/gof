package creational.builder.director;

import creational.builder.builder.CountingMazeBuilder;
import creational.builder.builder.MazeBuilder;
import creational.builder.builder.StandardMazeBuilder;
import creational.common.product.Maze;

/**
 * Director
 */
public class MazeGame {

    public Maze createMaze(MazeBuilder builder) {
        builder.buildMaze();
        builder.buildRoom(1);
        builder.buildRoom(2);
        builder.buildDoor(1, 2);
        return builder.getMaze();
    }

    /**
     * 복잡한 방의 미로
     */
    public Maze createComplexMaze(MazeBuilder builder) {
        builder.buildRoom(1);
        builder.buildRoom(1001);
        return builder.getMaze();
    }

    public static void main(String[] args) {
        StandardMazeBuilder builder1 = new StandardMazeBuilder();
        MazeGame game1 = new MazeGame();

        game1.createMaze(builder1);
        builder1.getMaze();


        CountingMazeBuilder builder2 = new CountingMazeBuilder();
        MazeGame game2 = new MazeGame();

        game2.createMaze(builder2);
        System.out.println(
                "the Maze has room count : " + builder2.getRooms() + " , doors count : " + builder2.getDoors());

    }
}
