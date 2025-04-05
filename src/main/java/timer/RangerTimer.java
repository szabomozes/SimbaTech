package timer;

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
            // TODO: célpont kovetése
        } else if (ranger.isNewPosition()) {
            startMovingToNewPosition();
        } else if (ranger.isMovingNewPosition()) {
            moveToNewPosition();
        }
    }

    private void startMovingToNewPosition() {
        ranger.setNewPosition(false);
        ranger.setMovingNewPosition(true);
    }

    private void moveToNewPosition() {
        if (hasReachedNewPosition()) {
            ranger.setMovingNewPosition(false);
            return;
        }

        int[] step = calculateStep();
        int[][] directions = generatePossibleDirections(step);

        int[] bestDirection = findBestDirection(directions);
        if (bestDirection != null) {
            updateRangerPosition(bestDirection[0], bestDirection[1]);
        }
    }

    private boolean hasReachedNewPosition() {
        return ranger.getX() == ranger.getNewPositionX() && ranger.getY() == ranger.getNewPositionY();
    }

    private int[] calculateStep() {
        int tempx = Math.min(Math.abs(ranger.getX() - ranger.getNewPositionX()), Speed.Instance.speedEnum.getRangerSteps());
        int tempy = Math.min(Math.abs(ranger.getY() - ranger.getNewPositionY()), Speed.Instance.speedEnum.getRangerSteps());
        return new int[] {tempx, tempy};
    }

    private int[][] generatePossibleDirections(int[] step) {
        return new int[][]{
                {step[0], step[1]}, {-step[0], step[1]}, {step[0], -step[1]}, {-step[0], -step[1]},
                {step[0], 0}, {-step[0], 0}, {0, step[1]}, {0, -step[1]}
        };
    }

    private int[] findBestDirection(int[][] directions) {
        int bestX = -1, bestY = -1;

        for (int[] dir : directions) {
            int newX = ranger.getX() + dir[0];
            int newY = ranger.getY() + dir[1];

            if (isValidPosition(newX, newY)) {
                int newDistance = calculateDistance(newX, newY);
                if (bestX == -1 || newDistance < calculateDistance(bestX, bestY)) {
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

    private int calculateDistance(int x, int y) {
        return Math.abs(x - ranger.getNewPositionX()) + Math.abs(y - ranger.getNewPositionY());
    }

    private void updateRangerPosition(int bestX, int bestY) {
        ranger.setX(bestX);
        ranger.setY(bestY);
    }
}
