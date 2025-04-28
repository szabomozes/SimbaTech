package entity.notmobile.plant;

import entity.notmobile.NotMobileEntity;
import safari.Safari;
import safari.Speed;

import java.awt.image.BufferedImage;

/**
 * Abstract base class for plant entities in the safari simulation, extending NotMobileEntity.
 * Represents stationary plants with attribute food.
 */
public abstract class Plant extends NotMobileEntity {

    private double food;

    /**
     * Constructs a Plant with specified coordinates and image.
     *
     * @param x     The x-coordinate of the plant.
     * @param y     The y-coordinate of the plant.
     * @param image The image representing the plant.
     */
    public Plant(int x, int y, BufferedImage image) {
        super(x, y, image);
        food = 100;

    }

    public void handlePlantMovement() {
        if (isAlive()) {
            if (food <= 0) {
                alive = false;
                return;
            }
            synchronized (this) {
                food += Speed.Instance.speedEnum.getPlantRegeneration();
                food = Math.min(food, 100);
            }
            if (food < 30) {
                image = getFood30();
            } else if (food < 60) {
                image = getFood60();
            } else {
                image = getFood100();
            }
        } else {
            Safari.Instance.removeEntityById(id);
            if (task != null && !task.isCancelled()) {
                task.cancel(false);
            }
        }
    }

    public void foodDecrement() {
        food -= Speed.Instance.speedEnum.getPlantDeeterioration();
    }

    protected abstract BufferedImage getFood30();
    protected abstract BufferedImage getFood60();
    protected abstract BufferedImage getFood100();
}