package entity.notmobile.plant;

import core.Resources;

/**
 * Represents a Baobab, a type of plant in the safari simulation.
 */
public class Baobab extends Plant {

    /**
     * Constructs a Baobab at the specified coordinates with the default baobab image.
     *
     * @param x The x-coordinate of the baobab.
     * @param y The y-coordinate of the baobab.
     */
    public Baobab(int x, int y) {
        super(x, y, Resources.Instance.baobab);
    }
}