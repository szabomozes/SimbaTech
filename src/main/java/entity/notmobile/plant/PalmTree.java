package entity.notmobile.plant;

import core.Resources;

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
}