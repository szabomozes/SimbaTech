package entity.mobile.person;

import core.Resources;

public class Ranger extends Person{
    private boolean target = false;
    private int targetX = 0;
    private int targetY = 0;
    private boolean newPosition = false;
    private int newPositionX = 0;
    private int newPositionY = 0;
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

    public boolean isNewPosition() {
        return newPosition;
    }

    public void setNewPosition(boolean newPosition) {
        this.newPosition = newPosition;
    }

    public int getNewPositionX() {
        return newPositionX;
    }

    public void setNewPositionX(int newPositionX) {
        this.newPositionX = newPositionX;
    }

    public int getNewPositionY() {
        return newPositionY;
    }

    public void setNewPositionY(int newPositionY) {
        this.newPositionY = newPositionY;
    }
}
