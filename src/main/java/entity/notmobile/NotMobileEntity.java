package entity.notmobile;

import entity.Entity;

import java.awt.image.BufferedImage;

/**
 * Abstract base class for non-mobile entities in the safari simulation, extending Entity.
 * Represents stationary entities that do not move within the environment.
 */
public abstract class NotMobileEntity extends Entity {

    /**
     * Constructs a NotMobileEntity with specified coordinates and image.
     *
     * @param x     The x-coordinate of the non-mobile entity.
     * @param y     The y-coordinate of the non-mobile entity.
     * @param image The image representing the non-mobile entity.
     */
    public NotMobileEntity(int x, int y, BufferedImage image) {
        super(x, y, image);
    }
}