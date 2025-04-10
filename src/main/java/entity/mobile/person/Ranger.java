package entity.mobile.person;

import core.Resources;
import entity.Entity;
import entity.mobile.animal.Leopard;
import entity.mobile.animal.Lion;
import entity.notmobile.Water;
import map.Coordinate;
import safari.RangerPayment;
import safari.Safari;
import safari.Speed;

import java.util.Arrays;

public class Ranger extends Person{
    private Entity targetEntity = null;
    private boolean target = false;
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
        return target;
    }

    public void setTarget(boolean target) {
        this.target = target;
    }

    public void setTargetEntity(Entity target) {
        this.targetEntity = target;
    }

    public Entity getTargetEntity() {
        return targetEntity;
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


    public void handleRangerMovement() {
        if (alive) {
            if (target) {
                isMovingToTarget = true;
                target = false;
            } else if (isMovingToTarget) {
                interactionWithTarget();
            } else if (newPosition) {
                startMovingToNewPosition();
            } else if (isMovingNewPosition) {
                moveToNewPosition();
            }
        } else {
            Safari.Instance.removeEntityById(id);
            if (task != null && !task.isCancelled()) {
                task.cancel(false);
            }
        }
    }

    private void interactionWithTarget() {
        if (targetEntity == null) return;

        if (calculateDistance(getX(), getY(), targetEntity.getX(), targetEntity.getY()) <= rifleRangeByPixel) {
            if(targetEntity instanceof Lion || targetEntity instanceof Leopard || targetEntity instanceof Poacher) {
                shooting(targetEntity);
            }
        } else {
            moveToTarget();
        }
    }

    private void shooting(Entity entity) {
        targetEntity = null;
        isMovingToTarget = false;
        RangerPayment.Instance.payForKilledEntity(entity);
        entity.setAlive(false);
    }

    private void moveToTarget() {
        if (targetEntity == null) return;

        int[] step = calculateStep(targetEntity.getX(), targetEntity.getY());
        int[][] directions = generatePossibleDirections(step);

        int[] bestDirection = findBestDirection(directions, targetEntity.getX(), targetEntity.getY());
        if (bestDirection != null) {
            updateRangerPosition(bestDirection[0], bestDirection[1]);
        }
    }

    private void startMovingToNewPosition() {
        newPosition = false;
        isMovingNewPosition = true;
    }

    private void moveToNewPosition() {
        if (hasReachedNewPosition(newPositionX, newPositionY)) {
            isMovingNewPosition = false;
            return;
        }

        int[] step = calculateStep(newPositionX, newPositionY);
        int[][] directions = generatePossibleDirections(step);

        int[] bestDirection = findBestDirection(directions, newPositionX, newPositionY);
        if (bestDirection != null) {
            updateRangerPosition(bestDirection[0], bestDirection[1]);
        }
    }

    private boolean hasReachedNewPosition(int goalX, int goalY) {
        return getX() == goalX && getY() == goalY;
    }

    private int[] calculateStep(int goalX, int goalY) {
        int tempx = Math.min(Math.abs(getX() - goalX), Speed.Instance.speedEnum.getRangerSteps());
        int tempy = Math.min(Math.abs(getY() - goalY), Speed.Instance.speedEnum.getRangerSteps());
        return new int[]{tempx, tempy};
    }

    private int[][] generatePossibleDirections(int[] step) {
        return new int[][]{
                {step[0], step[1]}, {-step[0], step[1]}, {step[0], -step[1]}, {-step[0], -step[1]},
                {step[0], 0}, {-step[0], 0}, {0, step[1]}, {0, -step[1]}
        };
    }

    private int[] findBestDirection(int[][] directions, int goalX, int goalY) {
        int bestX = -1, bestY = -1;

        for (int[] dir : directions) {
            int newX = getX() + dir[0];
            int newY = getY() + dir[1];

            if (isValidPosition(newX, newY)) {
                double newDistance = calculateDistance(newX, newY, goalX, goalY);
                if (bestX == -1 || newDistance < calculateDistance(bestX, bestY, goalX, goalY)) {
                    bestX = newX;
                    bestY = newY;
                }
            }
        }

        return bestX != -1 && bestY != -1 ? new int[]{bestX, bestY} : null;
    }

    private boolean isValidPosition(int newX, int newY) {
        int width = getWidth();
        int height = getHeight();

        return !overlapsWaterArea(newX, newY, width, height);
    }

    protected static boolean overlapsWaterArea(int x, int y, int width, int height) {
        for (Water water : Safari.Instance.getWaters()) {
            int waterX = water.getX();
            int waterY = water.getY();
            int waterWidth = water.getWidth();
            int waterHeight = water.getHeight();

            if (x + width > waterX && x < waterX + waterWidth &&
                    y + height > waterY && y < waterY + waterHeight) {
                return true;
            }
        }
        return false;
    }

    private double calculateDistance(int x, int y, int goalX, int goalY) {
        return Math.sqrt(Math.pow(x - goalX, 2) + Math.pow(y - goalY, 2));
    }

    private void updateRangerPosition(int newX, int newY) {
        setX(newX);
        setY(newY);
    }
}
