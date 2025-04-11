package entity.mobile.person;

import entity.mobile.MobileEntity;

import java.awt.image.BufferedImage;

/**
 * Abstract base class for person entities in the simulation, extending MobileEntity.
 */
public abstract class Person extends MobileEntity {

    /**
     * Constructs a Person with specified coordinates and image.
     *
     * @param x     The x-coordinate of the person.
     * @param y     The y-coordinate of the person.
     * @param image The image representing the person.
     */
    public Person(int x, int y, BufferedImage image) {
        super(x, y, image);
    }
}