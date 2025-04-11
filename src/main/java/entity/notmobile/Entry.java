package entity.notmobile;

import core.Resources;

/**
 * Represents an Entry, a type of non-mobile entity in the safari simulation, typically serving as an entrance point.
 */
public class Entry extends NotMobileEntity {

    /**
     * Constructs an Entry at the specified coordinates with the default entry image.
     *
     * @param x The x-coordinate of the entry.
     * @param y The y-coordinate of the entry.
     */
    public Entry(int x, int y) {
        super(x, y, Resources.Instance.entry);
    }
}