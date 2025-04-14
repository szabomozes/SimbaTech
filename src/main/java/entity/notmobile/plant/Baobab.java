package entity.notmobile.plant;

import core.Resources;

import java.awt.image.BufferedImage;

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

    @Override
    protected BufferedImage getFood30() {
        return Resources.Instance.baobab30;
    }

    @Override
    protected BufferedImage getFood60() {
        return Resources.Instance.baobab60;
    }

    @Override
    protected BufferedImage getFood100() {
        return Resources.Instance.baobab;
    }
}