package entity.notmobile;

import core.Resources;

/**
 * Represents a Water entity, a type of non-mobile entity in the safari simulation, typically serving as a water source.
 */
public class Water extends NotMobileEntity {

    /**
     * Constructs a Water entity at the specified coordinates with the default water image.
     *
     * @param x The x-coordinate of the water.
     * @param y The y-coordinate of the water.
     */
    public Water(int x, int y) {
        super(x, y, Resources.Instance.water);
    }
}