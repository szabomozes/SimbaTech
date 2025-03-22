package entity.mobile;

import core.Resources;


public class Zebra extends MobileEntity{

    public Zebra(int x, int y) {
        image = Resources.Instance.zebraBody;
        this.x = x;
        this.y = y;
        width = image.getWidth();
        height = image.getHeight();
    }

}
