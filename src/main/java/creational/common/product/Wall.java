package creational.common.product;

import creational.abstract_factory.product.MapSite;

/**
 * 방의 각 측면에 있을 수 있는 벽
 * AbstractProduct
 */
public class Wall extends MapSite implements Cloneable {
    public Wall() {

    }

    @Override
    public void enter() {

    }

    /**
     * Clone operation
     */
    @Override
    public Wall clone() {
        try {
            return (Wall) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Wall();
        }
    }
}
