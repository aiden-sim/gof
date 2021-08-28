package creational.prototype.prototype;

import creational.common.product.Wall;

/**
 * Concrete Prototype
 */
public class BombedWall extends Wall {

    private boolean hasBomb;

    public BombedWall() {

    }

    public BombedWall(BombedWall wall) {
        this.hasBomb = wall.hasBomb;
    }

    public boolean hasBomb() {
        return hasBomb;
    }

    @Override
    public BombedWall clone() {
        return new BombedWall(this);
    }
}
