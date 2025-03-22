package entity.mobile;

import core.Resources;


public class Lion extends MobileEntity{

    public Lion(int x, int y) {
        image = Resources.Instance.lionBody;
        this.x = x;
        this.y = y;
        width = image.getWidth();
        height = image.getHeight();
    }

}
