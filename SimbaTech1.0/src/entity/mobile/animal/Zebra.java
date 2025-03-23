package entity.mobile.animal;

import core.Resources;
import entity.mobile.MobileEntity;


public class Zebra extends Animal {

    public Zebra(int x, int y) {
        super(x, y, Resources.Instance.zebraBody);
    }

}
