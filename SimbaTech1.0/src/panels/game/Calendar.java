package panels.game;

import core.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Calendar {
    private int date = 1;
    private final int width;
    private final int height;
    private final BufferedImage image = Resources.Instance.calender;

    public Calendar() {
        width = image.getWidth();
        height = image.getHeight();
    }

    public boolean contains(int clickX, int clickY, int width) {
        int calendarX = width - this.width - 10;
        int calendarY = 10;
        return clickX >= calendarX && clickX <= calendarX + this.width &&
                clickY >= calendarY && clickY <= calendarY + this.height;
    }

    public void click() {
        date++;
        System.out.println("A dátum: " + date);
    }

    public void draw(Graphics g, int width) {
        int calendarX = width - this.width - 10;
        int calendarY = 10;

        g.drawImage(image, calendarX, calendarY, null);

        g.setFont(Resources.Instance.menu_font.deriveFont(35f));
        g.setColor(Color.BLACK);
        String dateText = String.format("%d", date);
        int side = 0;

        if (dateText.length() == 1) {
            side = 20;
        } else if (dateText.length() == 2) {
            side = 26;
        }

        g.drawString(dateText, calendarX + this.width - 10 - side, calendarY + 40);
    }
}
