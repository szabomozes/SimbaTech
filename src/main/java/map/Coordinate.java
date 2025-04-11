package map;

import java.util.Objects;

/**
 * Represents a 2D coordinate with x and y values in the safari simulation.
 */
public class Coordinate {
    public int x, y;

    /**
     * Constructs a Coordinate with the specified x and y values.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns a string representation of the coordinate.
     *
     * @return A string in the format "{x = [x], y = [y]}".
     */
    @Override
    public String toString() {
        return "{x = " + x + ", y = " + y + "}";
    }

    /**
     * Checks if this coordinate is equal to another object.
     *
     * @param o The object to compare with.
     * @return True if the object is a Coordinate with the same x and y values, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    /**
     * Generates a hash code for the coordinate based on its x and y values.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}