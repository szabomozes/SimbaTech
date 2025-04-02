package entity.notmobile;

import core.Resources;

public class Entry extends NotMobileEntity{
    public Entry(int x, int y) {
        super(x, y , Resources.Instance.entry);
        this.x = this.x + Resources.Instance.entry.getWidth() / 2;
        this.y = this.y + Resources.Instance.entry.getHeight() / 2;
    }
}
