package entity.mobile;

import core.Resources;


public class Leopard extends MobileEntity{

    public Leopard(int x, int y) {
        image = Resources.Instance.leopardBody;
        this.x = x;
        this.y = y;
        width = image.getWidth();
        height = image.getHeight();
    }

}
