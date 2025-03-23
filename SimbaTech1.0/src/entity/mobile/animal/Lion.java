package entity.mobile.animal;

import core.Resources;
import entity.mobile.MobileEntity;


public class Lion extends Animal {

    public Lion(int x, int y) {
        super(x, y, Resources.Instance.lionBody);
    }

}
