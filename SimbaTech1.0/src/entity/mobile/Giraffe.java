package entity.mobile;

import core.Resources;


public class Giraffe extends MobileEntity{

    public Giraffe(int x, int y) {
        image = Resources.Instance.giraffeBody;
        this.x = x;
        this.y = y;
        width = image.getWidth();
        height = image.getHeight();
    }

}
