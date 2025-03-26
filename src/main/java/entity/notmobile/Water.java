package entity.notmobile;

import core.Resources;

public class Water extends NotMobileEntity{
    public Water(int x, int y) {
        super(x, y , Resources.Instance.water);
    }
}
