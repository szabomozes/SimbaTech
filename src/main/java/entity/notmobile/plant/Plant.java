package entity.notmobile.plant;

import entity.notmobile.NotMobileEntity;

import java.awt.image.BufferedImage;

/**
 * Abstract base class for plant entities in the safari simulation, extending NotMobileEntity.
 * Represents stationary plants with attributes such as age, hunger, and thirst.
 */
public abstract class Plant extends NotMobileEntity {
    private int age;
    private int hunger;
    private int thirst;

    /**
     * Constructs a Plant with specified coordinates and image.
     *
     * @param x     The x-coordinate of the plant.
     * @param y     The y-coordinate of the plant.
     * @param image The image representing the plant.
     */
    public Plant(int x, int y, BufferedImage image) {
        super(x, y, image);
    }
}