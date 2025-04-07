package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    private static int idGenerator = 1;
    private int hello;

    public final int id = idGenerator++;
    protected BufferedImage image;
    protected int x, y;
    protected int width, height;
    private boolean alive = true;

    public Entity(int x, int y, BufferedImage image) {
        width = image.getWidth();
        height = image.getHeight();
        this.image = image;
        this.x = x - width / 2;
        this.y = y - height / 2;
    }

    public boolean contains(int clickX, int clickY) {
        return clickX >= x && clickX <= x + width && clickY >= y && clickY <= y + height;
    }

    public boolean enviromentContains(int clickX, int clickY) {
        return clickX >= x - image.getWidth() / 2 && clickX <= x + width + image.getWidth() / 2 && clickY >= y - image.getHeight() / 2 && clickY <= y + height + image.getHeight() / 2;
    }

    public void draw(Graphics g, int offsetX, int offsetY) {
        g.drawImage(image, x + offsetX, y + offsetY, null);
    }

    public int getY() {
        return y;
    }
    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
