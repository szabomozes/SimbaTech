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
    public BufferedImage ground0;
    public BufferedImage grass2;
    public BufferedImage grass3;
    public BufferedImage currentMap;
    public BufferedImage unicorn_left_1;
    public BufferedImage unicorn_left_2;

    private Resources() {}

    public void load() {
        menu();
        mapCreate();
        game();
    }

    private void menu() {

        try {
            menu_logo = ImageIO.read(new File("res/image/menu/logo/menu_logo.png"));
            menu_logo = resizeImage(menu_logo, 200);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            menu_font = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/menu/Jersey10-Regular.ttf"));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        try {
            menu_background = ImageIO.read(new File("res/image/menu/background/menu_background.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void mapCreate() {
        try {
            ground0 = ImageIO.read(new File("res/image/map/ground/0.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            grass2 = ImageIO.read(new File("res/image/map/grass/2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            grass3 = ImageIO.read(new File("res/image/map/grass/3.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void currentMap() {
        try {
            currentMap = ImageIO.read(new File("res/image/map/currentMap/map.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void game() {
        try {
            unicorn_left_1 = ImageIO.read(new File("res/image/game/entity/moving/unicorn/h1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            unicorn_left_2 = ImageIO.read(new File("res/image/game/entity/moving/unicorn/h2.png"));
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
