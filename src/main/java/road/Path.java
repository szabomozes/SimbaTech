package road;

import entity.notmobile.Water;
import map.Coordinate;
import safari.Safari;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Path} class represents a sequence of connected road segments.
 * It provides methods for building a path, checking for water overlap,
 * and retrieving various path-related data such as coordinates and pixel count.
 */
public class Path {
    private final List<Road> roads = new ArrayList<>();
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    /**
     * Constructs a new path starting from the specified coordinates.
     *
     * @param x the starting X coordinate
     * @param y the starting Y coordinate
     */
    public Path(int x, int y) {
        startX = x;
        startY = y;
    }

    /**
     * Returns the list of all road segments in this path.
     *
     * @return a list of {@code Road} objects
     */
    public List<Road> getRoads() {
        return roads;
    }

    /**
     * Sets the X coordinate of the end point for the next road segment.
     *
     * @param x the new end X coordinate
     */
    public void setEndX (int x) {
        endX = x;
    }

    /**
     * Sets the Y coordinate of the end point for the next road segment.
     *
     * @param y the new end Y coordinate
     */
    public void setEndY (int y) {
        endY = y;
    }

    /**
     * Creates and adds a new road segment from the current start coordinates
     * to the specified end coordinates.
     */
    public void addANewRoad() {
        roads.add(new Road(startX, startY, endX, endY));
    }

    /**
     * Checks if a new road segment from the current start point to the given
     * coordinates would intersect any water area on the map.
     *
     * @param newX the proposed end X coordinate
     * @param newY the proposed end Y coordinate
     * @return {@code true} if the road would overlap with water; {@code false} otherwise
     */
    public boolean overlapsWaterArea(int newX, int newY) {
        Road newRoad = new Road(startX, startY, newX, newY);
        List<Coordinate> coordinates = newRoad.getMid();

        for (Water water : Safari.Instance.getWaters()) {
            int waterX = water.getX();
            int waterY = water.getY();

            for (Coordinate coord : coordinates) {
                int x = coord.x;
                int y = coord.y;

                if (x >= waterX && x < waterX + water.getWidth() &&
                        y >= waterY && y < waterY + water.getHeight()) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Copies the current end coordinates to the start coordinates,
     * allowing chained road segment creation.
     */
    public void endCoorinateCopyToStartCoordinate () {
        startX = endX;
        startY = endY;
    }

    /**
     * Calculates the total number of pixels (coordinate points)
     * used across all road segments in the path.
     *
     * @return the total pixel count
     */
    public int getPixelCount() {
        int sum = 0;
        for (Road road : roads) {
            sum += road.getPixel();
        }
        return sum;
    }

    /**
     * Returns a list of all coordinates covered by the path's road segments.
     *
     * @return a list of {@code Coordinate} objects representing the path
     */
    public List<Coordinate> getPathCoordinations() {
        List<Coordinate> coordinates = new ArrayList<>();

        for (Road road : roads) {
            coordinates.addAll(road.getMid());
        }

        return coordinates;
    }
}
