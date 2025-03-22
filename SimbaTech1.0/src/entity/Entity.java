package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    protected BufferedImage image;
    protected int x, y;
    protected int width, height;

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
