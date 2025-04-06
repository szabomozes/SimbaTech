package timer;

import entity.Entity;
import entity.mobile.person.Ranger;
import map.Coordinate;
import panels.CardPanel;
import safari.Safari;
import safari.Speed;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class RangerTimer extends BasicTimer {

    private final Ranger ranger;

    public RangerTimer(Ranger ranger) {
        super(ranger.id);
        this.ranger = ranger;
    }

    @Override
    protected void update() {
        try {
            Instant now = Instant.now();
            Duration elapsed = Duration.between(lastUpdate, now);

            if (elapsed.toNanos() >= Speed.Instance.speedEnum.getRangerNanoSec()) {
                lastUpdate = now;
                handleRangerMovement();
                CardPanel.Instance.repaint();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handleRangerMovement() {
        if (ranger.isTarget()) {
            ranger.setMovingToTarget(true);
            ranger.setTarget(false);
        } else if (ranger.isMovingToTarget()) {
            interactionWithTarget();
        }
        else if (ranger.isNewPosition()) {
            startMovingToNewPosition();
        } else if (ranger.isMovingNewPosition()) {
            moveToNewPosition();
        }
    }

    private void interactionWithTarget() {
        Entity entity = ranger.getTargetEntity();
        if (calculateDistance(ranger.getX(), ranger.getY(), entity.getX(), entity.getY()) <= Ranger.rifleRangeByPixel) {
            shooting(entity);
        } else {
            moveToTarget();
        }
    }

    private void shooting(Entity entity) {
        ranger.setTargetEntity(null);
        ranger.setMovingToTarget(false);
        int id = entity.id;
        Safari.Instance.removeEntityById(id);
    }

    private void moveToTarget() {
        Entity entity = ranger.getTargetEntity();

        int[] step = calculateStep(entity.getX(), entity.getY());
        int[][] directions = generatePossibleDirections(step);

        int[] bestDirection = findBestDirection(directions, entity.getX(), entity.getY());
        if (bestDirection != null) {
            updateRangerPosition(bestDirection[0], bestDirection[1]);
        }
    }



    private void startMovingToNewPosition() {
        ranger.setNewPosition(false);
        ranger.setMovingNewPosition(true);
    }

    private void moveToNewPosition() {
        if (hasReachedNewPosition(ranger.getNewPositionX(), ranger.getNewPositionY())) {
            ranger.setMovingNewPosition(false);
            return;
        }

        int[] step = calculateStep(ranger.getNewPositionX(), ranger.getNewPositionY());
        int[][] directions = generatePossibleDirections(step);

        int[] bestDirection = findBestDirection(directions, ranger.getNewPositionX(), ranger.getNewPositionY());
        if (bestDirection != null) {
            updateRangerPosition(bestDirection[0], bestDirection[1]);
        }
    }

    private boolean hasReachedNewPosition(int goalX, int goalY) {
        return ranger.getX() == goalX && ranger.getY() == goalY;
    }

    private int[] calculateStep(int goalX, int goalY) {
        int tempx = Math.min(Math.abs(ranger.getX() - goalX), Speed.Instance.speedEnum.getRangerSteps());
        int tempy = Math.min(Math.abs(ranger.getY() - goalY), Speed.Instance.speedEnum.getRangerSteps());
        return new int[] {tempx, tempy};
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
            int newX = ranger.getX() + dir[0];
            int newY = ranger.getY() + dir[1];

            if (isValidPosition(newX, newY)) {
                double newDistance = calculateDistance(newX, newY, goalX, goalY);
                if (bestX == -1 || newDistance < calculateDistance(bestX, bestY, goalX, goalY)) {
                    bestX = newX;
                    bestY = newY;
                }
            }
        }

        return bestX != -1 && bestY != -1 ? new int[] {bestX, bestY} : null;
    }

    private boolean isValidPosition(int newX, int newY) {
        Coordinate[] coords = {
                new Coordinate(newX, newY),
                new Coordinate(newX + ranger.getWidth(), newY),
                new Coordinate(newX + ranger.getWidth(), newY + ranger.getHeight()),
                new Coordinate(newX, newY + ranger.getHeight())
        };

        return Arrays.stream(coords).noneMatch(c -> Safari.Instance.getWrongCoordinates().contains(c));
    }

    private double calculateDistance(int x, int y, int goalX, int goalY) {
        return Math.sqrt(Math.pow(x - goalX, 2) + Math.pow(y - goalY, 2));
    }

    private void updateRangerPosition(int bestX, int bestY) {
        ranger.setX(bestX);
        ranger.setY(bestY);
    }
}
