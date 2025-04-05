package entity.mobile.person;

import core.Resources;
import entity.Entity;

public class Ranger extends Person{
    private Entity target = null;
    private boolean isMovingToTarget = false;
    private boolean newPosition = false;
    private boolean isMovingNewPosition = false;
    private int newPositionX = 0;
    private int newPositionY = 0;
    private boolean selected = false;
    public final static int visualRangeByPixel = 600;
    public final static int rifleRangeByPixel = 300;

    public Ranger(int x, int y) {
        super(x, y, Resources.Instance.ranger);
    }

    public boolean isTarget() {
        return target != null;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }

    public Entity getTarget() {
        return target;
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

    public boolean isMovingNewPosition() {
        return isMovingNewPosition;
    }

    public void setMovingNewPosition(boolean movingNewPosition) {
        isMovingNewPosition = movingNewPosition;
    }

    public boolean isMovingToTarget() {
        return isMovingToTarget;
    }

    public void setMovingToTarget(boolean movingToTarget) {
        isMovingToTarget = movingToTarget;
    }
}
