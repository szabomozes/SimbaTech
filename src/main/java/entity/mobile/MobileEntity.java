package entity.mobile;

import entity.Entity;

import java.awt.image.BufferedImage;

/**
 * Abstract base class for mobile entities in the simulation, extending Entity.
 * Represents entities that can move within the safari environment.
 */
public abstract class MobileEntity extends Entity {

    /**
     * Constructs a MobileEntity with specified coordinates and image.
     *
     * @param x     The x-coordinate of the mobile entity.
     * @param y     The y-coordinate of the mobile entity.
     * @param image The image representing the mobile entity.
     */
    public MobileEntity(int x, int y, BufferedImage image) {
        super(x, y, image);
    }
}