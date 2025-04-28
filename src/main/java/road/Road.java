package road;

import core.Resources;
import map.Coordinate;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Road} class represents a single straight road segment between two coordinates.
 * It provides logic for pixel-level coordinate calculation and visual rendering.
 */
public class Road {
    public final int startX;
    public final int startY;
    public final int endX;
    public final int endY;

    private final List<Coordinate> mid;

    /**
     * Constructs a new {@code Road} from the given start to end coordinates.
     * The coordinates are clamped to the boundaries of the map.
     *
     * @param startX the starting X coordinate
     * @param startY the starting Y coordinate
     * @param endX   the ending X coordinate
     * @param endY   the ending Y coordinate
     */
    public Road(int startX, int startY, int endX, int endY) {
        int minX = 0;
        int maxX = Resources.Instance.map.getWidth();
        int minY = 0;
        int maxY = Resources.Instance.map.getHeight();
        startX = Math.max(minX, startX);
        startX = Math.min(maxX, startX);
        startY = Math.max(minY, startY);
        startY = Math.min(maxY, startY);
        endX = Math.max(minX, endX);
        endX = Math.min(maxX, endX);
        endY = Math.max(minY, endY);
        endY = Math.min(maxY, endY);
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.mid = calculateMidPoints();
    }

    /**
     * Calculates intermediate coordinates (pixels) along the road segment using linear interpolation.
     *
     * @return a list of {@code Coordinate} points from start to end
     */
    private List<Coordinate> calculateMidPoints() {
        List<Coordinate> points = new ArrayList<>();
        int dx = endX - startX;
        int dy = endY - startY;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        if (steps == 0) {
            points.add(new Coordinate(startX, startY));
            return points;
        }

        for (int i = 0; i <= steps; i++) {
            int x = startX + i * dx / steps;
            int y = startY + i * dy / steps;
            points.add(new Coordinate(x, y));
        }
        return points;
    }

    /**
     * Returns the number of pixels that make up the road (its length in coordinate units).
     *
     * @return the number of coordinates in the road
     */
    public int getPixel() {
        int sum = 0;
        sum += mid.size();
        return sum;
    }

    /**
     * Retrieves the list of all intermediate coordinates along the road.
     *
     * @return a list of {@code Coordinate} objects
     */
    public List<Coordinate> getMid() {
        return mid;
    }

    /**
     * Draws the road segment using a thick rounded line on the given graphics context.
     *
     * @param g       the graphics context
     * @param offsetX horizontal offset to apply when drawing
     * @param offsetY vertical offset to apply when drawing
     */
    public void draw(Graphics g, int offsetX, int offsetY) {
        Graphics2D g2d = (Graphics2D) g;
        Stroke originalStroke = g2d.getStroke();

        g2d.setStroke(new BasicStroke(70, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(startX + offsetX, startY + offsetY, endX + offsetX, endY + offsetY);

        g2d.setStroke(originalStroke);
    }
}
