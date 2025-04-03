package entity.mobile.person;

import core.Resources;

public class Ranger extends Person{
    private boolean target = false;
    private int targetX = 0;
    private int targetY = 0;
    private boolean selected = false;

    public Ranger(int x, int y) {
        super(x, y, Resources.Instance.ranger);
    }

    public boolean isTarget() {
        return target;
    }

    public void setTarget(boolean target) {
        this.target = target;
    }

    public int getTargetX() {
        return targetX;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
