package structural.flyweight.example;

import java.util.HashMap;
import java.util.Map;

public class FlyweightFactory {
    private Map<Integer, Flyweight> flyweights = new HashMap<Integer, Flyweight>();

    public ConcreteFlyweight getFlyweight(int key) {
        ConcreteFlyweight flyweight = (ConcreteFlyweight) flyweights.get(key);
        if (flyweight == null) {
            flyweight = new ConcreteFlyweight(new IntrinsicState("InternalState" + key));
            flyweights.put(key, flyweight);
        }
        return flyweight;
    }
}
