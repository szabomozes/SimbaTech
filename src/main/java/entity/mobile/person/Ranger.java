package entity.mobile.person;

import core.Resources;
import entity.Entity;
import entity.mobile.animal.Leopard;
import entity.mobile.animal.Lion;
import entity.notmobile.Water;
import safari.RangerPayment;
import safari.Safari;
import safari.Speed;


/**
 * Represents a Ranger, a type of person in the safari simulation responsible for tracking and neutralizing threats
 * such as lions, leopards, or poachers, and moving to designated positions.
 */
public class Ranger extends Person {
    private Entity targetEntity = null;
    private boolean target = false;
    private boolean isMovingToTarget = false;
    private boolean newPosition = false;
    private boolean isMovingNewPosition = false;
    private int newPositionX = 0;
    private int newPositionY = 0;
    private boolean selected = false;
    public final static int rifleRangeByPixel = 300;

    /**
     * Constructs a Ranger at the specified coordinates with the default ranger image.
     *
     * @param x The x-coordinate of the ranger.
     * @param y The y-coordinate of the ranger.
     */
    public Ranger(int x, int y) {
        super(x, y, Resources.Instance.ranger);
    }

    /**
     * Checks if the ranger has a target assigned.
     *
     * @return True if a target is assigned, false otherwise.
     */
    public boolean isTarget() {
        return target;
    }

    /**
     * Sets whether the ranger has a target.
     *
     * @param target True if a target is assigned, false otherwise.
     */
    public void setTarget(boolean target) {
        this.target = target;
    }

    /**
     * Sets the target entity for the ranger to pursue.
     *
     * @param target The entity to target.
     */
    public void setTargetEntity(Entity target) {
        this.targetEntity = target;
    }

    /**
     * Gets the current target entity of the ranger.
     *
     * @return The target entity, or null if none is assigned.
     */
    public Entity getTargetEntity() {
        return targetEntity;
    }

    /**
     * Checks if the ranger is selected.
     *
     * @return True if the ranger is selected, false otherwise.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Sets whether the ranger is selected.
     *
     * @param selected True if the ranger is selected, false otherwise.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * Checks if the ranger has a new position to move to.
     *
     * @return True if a new position is set, false otherwise.
     */
    public boolean isNewPosition() {
        return newPosition;
    }

    /**
     * Sets whether the ranger has a new position to move to.
     *
     * @param newPosition True if a new position is set, false otherwise.
     */
    public void setNewPosition(boolean newPosition) {
        this.newPosition = newPosition;
    }

    /**
     * Gets the x-coordinate of the new position.
     *
     * @return The x-coordinate of the new position.
     */
    public int getNewPositionX() {
        return newPositionX;
    }

    /**
     * Sets the x-coordinate of the new position.
     *
     * @param newPositionX The x-coordinate to set.
     */
    public void setNewPositionX(int newPositionX) {
        this.newPositionX = newPositionX;
    }

    /**
     * Gets the y-coordinate of the new position.
     *
     * @return The y-coordinate of the new position.
     */
    public int getNewPositionY() {
        return newPositionY;
    }

    /**
     * Sets the y-coordinate of the new position.
     *
     * @param newPositionY The y-coordinate to set.
     */
    public void setNewPositionY(int newPositionY) {
        this.newPositionY = newPositionY;
    }

    /**
     * Checks if the ranger is moving to a new position.
     *
     * @return True if moving to a new position, false otherwise.
     */
    public boolean isMovingNewPosition() {
        return isMovingNewPosition;
    }

    /**
     * Sets whether the ranger is moving to a new position.
     *
     * @param movingNewPosition True if moving to a new position, false otherwise.
     */
    public void setMovingNewPosition(boolean movingNewPosition) {
        isMovingNewPosition = movingNewPosition;
    }

    /**
     * Checks if the ranger is moving toward a target.
     *
     * @return True if moving to a target, false otherwise.
     */
    public boolean isMovingToTarget() {
        return isMovingToTarget;
    }

    /**
     * Sets whether the ranger is moving toward a target.
     *
     * @param movingToTarget True if moving to a target, false otherwise.
     */
    public void setMovingToTarget(boolean movingToTarget) {
        isMovingToTarget = movingToTarget;
    }

    /**
     * Manages the ranger's movement and behavior based on its state.
     * Handles movement toward a target or new position, and removes the ranger from the simulation if no longer alive.
     */
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

    /**
     * Handles interaction with the target entity, either shooting it if within rifle range
     * or moving closer if out of range.
     */
    private void interactionWithTarget() {
        if (targetEntity == null) return;

        if (calculateDistance(getX(), getY(), targetEntity.getX(), targetEntity.getY()) <= rifleRangeByPixel) {
            if (targetEntity instanceof Lion || targetEntity instanceof Leopard || targetEntity instanceof Poacher) {
                shooting(targetEntity);
            }
        } else {
            moveToTarget();
        }
    }

    /**
     * Shoots the target entity, neutralizing it and updating payment records.
     *
     * @param entity The entity to shoot.
     */
    private void shooting(Entity entity) {
        targetEntity = null;
        isMovingToTarget = false;
        RangerPayment.Instance.payForKilledEntity(entity);
        entity.setAlive(false);
    }

    /**
     * Moves the ranger toward the target entity by selecting the best direction that avoids water.
     */
    private void moveToTarget() {
        if (targetEntity == null) return;

        int[] step = calculateStep(targetEntity.getX(), targetEntity.getY());
        int[][] directions = generatePossibleDirections(step);

        int[] bestDirection = findBestDirection(directions, targetEntity.getX(), targetEntity.getY());
        if (bestDirection != null) {
            updateRangerPosition(bestDirection[0], bestDirection[1]);
        }
    }

    /**
     * Initiates movement to a new designated position.
     */
    private void startMovingToNewPosition() {
        newPosition = false;
        isMovingNewPosition = true;
    }

    /**
     * Moves the ranger toward the new designated position, stopping when reached.
     */
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

    /**
     * Checks if the ranger has reached the designated new position.
     *
     * @param goalX The x-coordinate of the goal.
     * @param goalY The y-coordinate of the goal.
     * @return True if the position is reached, false otherwise.
     */
    private boolean hasReachedNewPosition(int goalX, int goalY) {
        return getX() == goalX && getY() == goalY;
    }

    /**
     * Calculates the step size for movement based on the distance to the goal.
     *
     * @param goalX The x-coordinate of the goal.
     * @param goalY The y-coordinate of the goal.
     * @return An array containing the x and y step sizes.
     */
    private int[] calculateStep(int goalX, int goalY) {
        int tempx = Math.min(Math.abs(getX() - goalX), Speed.Instance.speedEnum.getRangerSteps());
        int tempy = Math.min(Math.abs(getY() - goalY), Speed.Instance.speedEnum.getRangerSteps());
        return new int[]{tempx, tempy};
    }

    /**
     * Generates possible movement directions based on the calculated step size.
     *
     * @param step An array containing the x and y step sizes.
     * @return A 2D array of possible direction coordinates.
     */
    private int[][] generatePossibleDirections(int[] step) {
        return new int[][]{
                {step[0], step[1]}, {-step[0], step[1]}, {step[0], -step[1]}, {-step[0], -step[1]},
                {step[0], 0}, {-step[0], 0}, {0, step[1]}, {0, -step[1]}
        };
    }

    /**
     * Finds the best direction to move toward the goal, avoiding water areas.
     *
     * @param directions The possible movement directions.
     * @param goalX      The x-coordinate of the goal.
     * @param goalY      The y-coordinate of the goal.
     * @return The best direction as an array of x and y coordinates, or null if no valid direction exists.
     */
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

    /**
     * Checks if a position is valid by ensuring it does not overlap with water.
     *
     * @param newX   The x-coordinate to check.
     * @param newY   The y-coordinate to check.
     * @return True if the position is valid, false otherwise.
     */
    private boolean isValidPosition(int newX, int newY) {
        int width = getWidth();
        int height = getHeight();

        return !overlapsWaterArea(newX, newY, width, height);
    }

    /**
     * Checks if a given position overlaps with any water area.
     *
     * @param x      The x-coordinate to check.
     * @param y      The y-coordinate to check.
     * @param width  The width of the entity.
     * @param height The height of the entity.
     * @return True if the position overlaps with water, false otherwise.
     */
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

    /**
     * Calculates the Euclidean distance between two points.
     *
     * @param x     The x-coordinate of the first point.
     * @param y     The y-coordinate of the first point.
     * @param goalX The x-coordinate of the second point.
     * @param goalY The y-coordinate of the second point.
     * @return The distance between the two points.
     */
    private double calculateDistance(int x, int y, int goalX, int goalY) {
        return Math.sqrt(Math.pow(x - goalX, 2) + Math.pow(y - goalY, 2));
    }

    /**
     * Updates the ranger's position to the specified coordinates.
     *
     * @param newX The new x-coordinate.
     * @param newY The new y-coordinate.
     */
    private void updateRangerPosition(int newX, int newY) {
        setX(newX);
        setY(newY);
    }
}