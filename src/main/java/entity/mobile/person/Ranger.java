package entity.mobile.person;

import core.Resources;
import entity.Entity;
import map.Coordinate;

import java.util.List;

public class Ranger extends Person{
    private Entity target = null;
    private int targetX = 0;
    private int targetY = 0;
    private boolean newPosition = false;
    private boolean isMovingNewPosition = false;
    private List<Coordinate> movingCoordinates = null;
    private int newPositionX = 0;
    private int newPositionY = 0;
    private boolean selected = false;

    public Ranger(int x, int y) {
        super(x, y, Resources.Instance.ranger);
    }

    public boolean isTarget() {
        return target != null;
    }

    public void setTarget(Entity target) {
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

    public boolean isMovingNewPosition() {
        return isMovingNewPosition;
    }

    public void setMovingNewPosition(boolean movingNewPosition) {
        isMovingNewPosition = movingNewPosition;
    }

    public List<Coordinate> getMovingCoordinates() {
        return movingCoordinates;
    }

    public void setMovingCoordinates(List<Coordinate> movingCoordinates) {
        this.movingCoordinates = movingCoordinates;
    }

    public Coordinate deleteLastCoordinateFromMovingCoordinates() {
        return movingCoordinates.removeLast();
    }
}
