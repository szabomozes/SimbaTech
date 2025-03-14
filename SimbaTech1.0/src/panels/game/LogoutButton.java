package panels.game;

import core.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LogoutButton {
    private final int x = 10;
    private final int y = 10;
    private final int width;
    private final int height;
    private final BufferedImage image = Resources.Instance.logoutButton;

    public LogoutButton() {
        width = image.getWidth();
        height = image.getHeight();
    }

    public boolean contains(int clickX, int clickY) {
        return clickX >= x && clickX <= x + width && clickY >= y && clickY <= y + height;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }
}
