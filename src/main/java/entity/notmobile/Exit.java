package entity.notmobile;

import core.Resources;

public class Exit extends NotMobileEntity{
    public Exit(int x, int y) {
        super(x, y , Resources.Instance.exit);
        this.x = this.x - Resources.Instance.exit.getWidth() / 2;
        this.y = this.y - Resources.Instance.exit.getHeight() / 2;
    }
}
