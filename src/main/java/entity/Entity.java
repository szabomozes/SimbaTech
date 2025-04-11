package entity;

import core.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ScheduledFuture;

/**
 * Abstract base class for all entities in the safari simulation, providing common properties and behaviors.
 */
public abstract class Entity {

    private static int idGenerator = 1;
    private int hello;

    public final int id = idGenerator++;
    protected BufferedImage image;
    protected int x, y;
    protected int width, height;
    protected boolean alive = true;
    protected ScheduledFuture<?> task = null;
    protected ScheduledFuture<?> task2 = null;

    /**
     * Constructs an Entity with specified coordinates and image, centering the entity and ensuring it stays within map boundaries.
     *
     * @param x     The x-coordinate of the entity's center.
     * @param y     The y-coordinate of the entity's center.
     * @param image The image representing the entity.
     */
    public Entity(int x, int y, BufferedImage image) {
        width = image.getWidth();
        height = image.getHeight();
        this.image = image;
        this.x = x - width / 2;
        this.y = y - height / 2;
        this.x = Math.max(0, this.x);
        this.y = Math.max(0, this.y);
        this.x = Math.min(Resources.Instance.map.getWidth(), this.x);
        this.y = Math.min(Resources.Instance.map.getHeight(), this.y);
    }

    /**
     * Checks if a given point lies within the entity's bounding box.
     *
     * @param clickX The x-coordinate of the point.
     * @param clickY The y-coordinate of the point.
     * @return True if the point is within the entity's bounds, false otherwise.
     */
    public boolean contains(int clickX, int clickY) {
        return clickX >= x && clickX <= x + width && clickY >= y && clickY <= y + height;
    }

    /**
     * Checks if a given point lies within an extended environment around the entity, defined by the image dimensions.
     *
     * @param clickX The x-coordinate of the point.
     * @param clickY The y-coordinate of the point.
     * @return True if the point is within the extended environment, false otherwise.
     */
    public boolean enviromentContains(int clickX, int clickY) {
        return clickX >= x - image.getWidth() / 2 && clickX <= x + width + image.getWidth() / 2 && clickY >= y - image.getHeight() / 2 && clickY <= y + height + image.getHeight() / 2;
    }

    /**
     * Draws the entity's image on the provided graphics context with an offset.
     *
     * @param g       The graphics context to draw on.
     * @param offsetX The x-offset for drawing.
     * @param offsetY The y-offset for drawing.
     */
    public void draw(Graphics g, int offsetX, int offsetY) {
        g.drawImage(image, x + offsetX, y + offsetY, null);
    }

    /**
     * Gets the y-coordinate of the entity's top-left corner.
     *
     * @return The y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the x-coordinate of the entity's top-left corner.
     *
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the y-coordinate of the entity's top-left corner.
     *
     * @param y The y-coordinate to set.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets the x-coordinate of the entity's top-left corner.
     *
     * @param x The x-coordinate to set.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the width of the entity.
     *
     * @return The width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the entity.
     *
     * @return The height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Checks if the entity is alive.
     *
     * @return True if the entity is alive, false otherwise.
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Sets whether the entity is alive.
     *
     * @param alive True if the entity is alive, false otherwise.
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Gets the primary scheduled task associated with the entity.
     *
     * @return The scheduled task, or null if none is set.
     */
    public ScheduledFuture<?> getTask() {
        return task;
    }

    /**
     * Sets the primary scheduled task for the entity.
     *
     * @param task The scheduled task to set.
     */
    public void setTask(ScheduledFuture<?> task) {
        this.task = task;
    }

    /**
     * Sets the secondary scheduled task for the entity.
     *
     * @param task2 The secondary scheduled task to set.
     */
    public void setTask2(ScheduledFuture<?> task2) {
        this.task2 = task2;
    }
}