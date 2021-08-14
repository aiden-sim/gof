package creational.abstract_factory.factory;

import creational.abstract_factory.production.*;
import creational.abstract_factory.production.Spell;
import creational.common.production.Door;
import creational.common.production.Maze;
import creational.common.production.Room;
import creational.common.production.Wall;

/**
 * ConcreteFactory
 */
public class EnchantedMazeFactory implements MazeFactory {
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
