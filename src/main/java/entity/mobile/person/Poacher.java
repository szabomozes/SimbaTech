package entity.mobile.person;

import core.Resources;
import entity.Entity;
import safari.Safari;
import safari.Speed;

import java.util.List;
import java.util.Random;

public class Poacher extends Person {
    private double targetX, targetY;
    private double currentX, currentY;
    public final static int rifleRange = 200;
    private boolean isVisible;
    private int movingRange = 400;

    // TODO PoacherSteps beállítása

    public Poacher(int x, int y) {
        super(x, y, Resources.Instance.poacher);
        this.currentX = x;
        this.currentY = y;
        this.targetX = x;
        this.targetY = y;
        isVisible = false;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void hide() {
        setVisible(false);
    }

    public void reveal() {
        setVisible(true);
    }

    public void poacherVisibility() {
        if (!alive) {
            Safari.Instance.removeEntityById(id);
            if (task != null && !task.isCancelled()) {
                task.cancel(false);
                task2.cancel(false);
            }
        }
        List<Entity> entities = Safari.Instance.rangersAndJeeps();
        boolean seen = false;

        for (Entity e : entities) {
            if (x - Poacher.rifleRange < e.getX() &&
                    x + e.getWidth() + Poacher.rifleRange >= e.getX() &&
                    y - Poacher.rifleRange < e.getY() &&
                    y + e.getHeight() + Poacher.rifleRange >= e.getY()) {
                reveal();
                seen = true;
                break;
            }
        }
        if (!seen) {
            hide();
        }
    }

    private void setTargetXAndY(double currentX, double currentY) {
        Random rand = new Random();
        double mapWidth = Resources.Instance.map.getWidth();
        double mapHeight = Resources.Instance.map.getHeight();

        double minX = Math.max(0.0, currentX - movingRange);
        double maxX = Math.min(mapWidth, currentX + movingRange);
        double minY = Math.max(0.0, currentY - movingRange);
        double maxY = Math.min(mapHeight, currentY + movingRange);

        do {
            targetX = rand.nextDouble(maxX - minX) + minX;
            targetY = rand.nextDouble(maxY - minY) + minY;
        } while (Math.abs(targetX - currentX) < 1 && Math.abs(targetY - currentY) < 1);
    }

    public void move() {
        if (!alive) {
            Safari.Instance.removeEntityById(id);
            if (task != null && !task.isCancelled()) {
                task.cancel(false);
                task2.cancel(false);
            }
        }
        if (Math.abs(currentX - targetX) < 1 && Math.abs(currentY - targetY) < 1) {
            setTargetXAndY(currentX, currentY);
        }

        double dx = targetX - currentX;
        double dy = targetY - currentY;
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance > 0) {
            double step = Speed.Instance.speedEnum.getPoacherSteps();
            double nextX = currentX + (dx / distance) * step;
            double nextY = currentY + (dy / distance) * step;

            if (!Ranger.overlapsWaterArea((int) nextX, (int) nextY, this.getWidth(), this.getHeight())) {
                currentX = nextX;
                currentY = nextY;
            } else {
                teleport();
                return;
            }
            searchAnimalsToKill();
        }

        this.x = (int) currentX;
        this.y = (int) currentY;
    }

    private void teleport() {
        Random rand = new Random();
        do {
            currentX = rand.nextDouble(Resources.Instance.map.getWidth());
            currentY = rand.nextDouble(Resources.Instance.map.getHeight());
        } while (!Ranger.overlapsWaterArea((int) currentX, (int) currentY, this.getWidth(), this.getHeight()));
        setTargetXAndY(currentX, currentY);
    }

    public void searchAnimalsToKill() {
        List<Entity> animals = Safari.Instance.getAnimals();

        for (Entity e : animals) {
            double distance = Math.sqrt(Math.pow(currentX - e.getX(), 2) + Math.pow(currentY - e.getY(), 2));
            if (distance <= rifleRange) {
                shoot(e);
                // Egyszerre csak egy állatot tud megölni!
                break;
            }
        }
    }

    public void shoot(Entity animal) {
        animal.setAlive(false);
    }
}
