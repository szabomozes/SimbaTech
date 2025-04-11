package entity.notmobile.plant;

import core.Resources;

/**
 * Represents a Pancium, a type of plant in the safari simulation.
 */
public class Pancium extends Plant {

    /**
     * Constructs a Pancium at the specified coordinates with the default pancium image.
     *
     * @param x The x-coordinate of the pancium.
     * @param y The y-coordinate of the pancium.
     */
    public Pancium(int x, int y) {
        super(x, y, Resources.Instance.pancium);
    }
}