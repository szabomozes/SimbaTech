package core;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Resources {
    public static Resources Instance = new Resources();

    public Font menu_font;
    public BufferedImage menu_background;
    public BufferedImage menu_logo;
    public BufferedImage map;
    public BufferedImage logoutButton;
    public BufferedImage calender;

    private Resources() {}

    public void load() {
        menu();
        map();
        game();
    }

    private void menu() {

        try {
            menu_font = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/menu/Jersey10-Regular.ttf"));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        try {
            menu_logo = ImageIO.read(new File("res/icons/menu/logo/menu_logo.png"));
            menu_logo = resizeImage(menu_logo, 200);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            menu_background = ImageIO.read(new File("res/icons/menu/background/menu_background.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void map() {
        try {
            map = ImageIO.read(new File("res/icons/game-panel/map.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void game() {
        try {
            logoutButton = ImageIO.read(new File("res/icons/game-panel/logout.png"));
            logoutButton = resizeImage(logoutButton, 50);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            calender = ImageIO.read(new File("res/icons/game-panel/calendar.png"));
            calender = resizeImage(calender, 50);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedImage resizeImage(BufferedImage original, int height) {
        int newWidth = (int) ((double) original.getWidth() / original.getHeight() * height); // Arányos szélesség
        Image scaled = original.getScaledInstance(newWidth, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(newWidth, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(scaled, 0, 0, null);
        g2d.dispose();
        return resized;
    }

}
