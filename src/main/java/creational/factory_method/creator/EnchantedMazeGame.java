package creational.factory_method.creator;

import creational.common.product.Door;
import creational.common.product.Maze;
import creational.common.product.Room;
import creational.common.product.Wall;
import creational.factory_method.product.DoorNeedingSpell;
import creational.factory_method.product.EnchantedRoom;
import creational.factory_method.product.Spell;

public class EnchantedMazeGame extends MazeGame {
    @Override
    public Maze makeMaze() {
        return new Maze();
    }

    @Override
    public Wall makeWall() {
        return new Wall();
    }

    @Override
    public Room makeRoom(int n) {
        return new EnchantedRoom(n, castSpell());
    }


    @Override
    public Door makeDoor(Room r1, Room r2) {
        return new DoorNeedingSpell(r1, r2);
    }

    protected Spell castSpell() {
        return new Spell();
    }
}
