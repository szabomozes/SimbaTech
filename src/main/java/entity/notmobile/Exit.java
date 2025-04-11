package entity.notmobile;

import core.Resources;

/**
 * Represents an Exit, a type of non-mobile entity in the safari simulation, typically serving as an exit point.
 */
public class Exit extends NotMobileEntity {

    /**
     * Constructs an Exit at the specified coordinates with the default exit image.
     *
     * @param x The x-coordinate of the exit.
     * @param y The y-coordinate of the exit.
     */
    public Exit(int x, int y) {
        super(x, y, Resources.Instance.exit);
    }
}