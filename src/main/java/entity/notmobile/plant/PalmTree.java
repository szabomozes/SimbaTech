package entity.notmobile.plant;

import core.Resources;

import java.awt.image.BufferedImage;

/**
 * Represents a PalmTree, a type of plant in the safari simulation.
 */
public class PalmTree extends Plant {

    /**
     * Constructs a PalmTree at the specified coordinates with the default palm tree image.
     *
     * @param x The x-coordinate of the palm tree.
     * @param y The y-coordinate of the palm tree.
     */
    public PalmTree(int x, int y) {
        super(x, y, Resources.Instance.palmTree);
    }

    @Override
    protected BufferedImage getFood30() {
        return Resources.Instance.palmTree30;
    }

    @Override
    protected BufferedImage getFood60() {
        return Resources.Instance.palmTree60;
    }

    @Override
    protected BufferedImage getFood100() {
        return Resources.Instance.palmTree;
    }
}