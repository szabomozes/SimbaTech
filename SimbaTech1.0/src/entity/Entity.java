package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    protected BufferedImage image;
    protected int x, y;
    protected int width, height;

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

    public void draw(Graphics g, int offsetX, int offsetY) {
        g.drawImage(image, x + offsetX, y + offsetY, null);
    }

    public int getY() {
        return y;
    }
}
