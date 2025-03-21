package core;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Resources {
    public static final Resources Instance = new Resources();

    public Font menu_font;
    public BufferedImage menu_background;
    public BufferedImage menu_logo;
    public BufferedImage map;
    public BufferedImage logoutButton;
    public BufferedImage calender;
    public BufferedImage lionButton;
    public BufferedImage leopardButton;
    public BufferedImage zebraButton;
    public BufferedImage giraffeButon;
    public BufferedImage baobabButton;
    public BufferedImage palmTreeButton;
    public BufferedImage panciumButton;
    public BufferedImage waterButton;
    public BufferedImage jeepButton;
    public BufferedImage rangerButton;
    public BufferedImage speedEadle;
    public BufferedImage speedHippopotamus;
    public BufferedImage speedSnail;

    private Resources() {}

    public void load() {
        menu();
        map();
        game();
        toolBar();
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

    private void toolBar() {
        try {
            lionButton = ImageIO.read(new File("res/icons/toolkit/shop/animal/face/lion-face.png"));
            lionButton = resizeImage(lionButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            leopardButton = ImageIO.read(new File("res/icons/toolkit/shop/animal/face/leopard-face.png"));
            leopardButton = resizeImage(leopardButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            zebraButton = ImageIO.read(new File("res/icons/toolkit/shop/animal/face/zebra-face.png"));
            zebraButton = resizeImage(zebraButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            giraffeButon = ImageIO.read(new File("res/icons/toolkit/shop/animal/face/giraffe-face.png"));
            giraffeButon = resizeImage(giraffeButon, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            baobabButton = ImageIO.read(new File("res/icons/toolkit/shop/plant/baobab.png"));
            baobabButton = resizeImage(baobabButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            palmTreeButton = ImageIO.read(new File("res/icons/toolkit/shop/plant/palm-tree.png"));
            palmTreeButton = resizeImage(palmTreeButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            panciumButton = ImageIO.read(new File("res/icons/toolkit/shop/plant/pancium.png"));
            panciumButton = resizeImage(panciumButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            waterButton = ImageIO.read(new File("res/icons/toolkit/shop/object/water.png"));
            waterButton = resizeImage(waterButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            jeepButton = ImageIO.read(new File("res/icons/toolkit/shop/object/jeep.png"));
            jeepButton = resizeImage(jeepButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            rangerButton = ImageIO.read(new File("res/icons/toolkit/shop/person/ranger.png"));
            rangerButton = resizeImage(rangerButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            speedEadle = ImageIO.read(new File("res/icons/toolkit/speed/eagle.png"));
            speedEadle = resizeImage(speedEadle, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            speedHippopotamus = ImageIO.read(new File("res/icons/toolkit/speed/hippopotamus.png"));
            speedHippopotamus = resizeImage(speedHippopotamus, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            speedSnail = ImageIO.read(new File("res/icons/toolkit/speed/snail.png"));
            speedSnail = resizeImage(speedSnail, 60);
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
