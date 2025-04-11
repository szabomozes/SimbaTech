package core;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The {@code Resources} class manages loading and storing various resources such as images, fonts, and other assets used in the application.
 * It provides a singleton instance {@link #Instance} for easy access to these resources.
 */
public class Resources {

    /** Singleton instance of the Resources class. */
    public static final Resources Instance = new Resources();

    // Fields for storing resources
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
    public BufferedImage badFeddBack;
    public BufferedImage loseFeddBack;
    public BufferedImage winFeddBack;
    public BufferedImage sellButton;
    public BufferedImage binButton;
    public BufferedImage roadTableButton;
    public BufferedImage coinStack;
    public BufferedImage minimap;
    public BufferedImage ground;
    public BufferedImage grass1;
    public BufferedImage grass2;
    public BufferedImage lionBody;
    public BufferedImage leopardBody;
    public BufferedImage zebraBody;
    public BufferedImage giraffeBody;
    public BufferedImage saveButton;
    public BufferedImage deleteButton;
    public BufferedImage hourglass;
    public BufferedImage palmTree;
    public BufferedImage baobab;
    public BufferedImage pancium;
    public BufferedImage water;
    public BufferedImage ranger;
    public BufferedImage poacher;
    public BufferedImage jeep, jeep_back;
    public BufferedImage entry;
    public BufferedImage exit;
    public BufferedImage mainlogo;
    public BufferedImage info;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * Use the singleton instance {@link #Instance} to access resources.
     */
    private Resources() {}

    /**
     * Loads all necessary resources including images, fonts, and other assets.
     */
    public void load() {
        menu();
        mapCreate();
        game();
        toolBar();
        animals();
        persons();
        feedBacks();
    }

    /**
     * Loads menu-related resources such as fonts and images.
     */
    private void menu() {
        try {
            menu_font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/res/font/Jersey10-Regular.ttf"));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        try {
            menu_logo = ImageIO.read(new File("src/main/res/icons/menu/logo/menu_logo.png"));
            menu_logo = resizeImage(menu_logo, 200);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            menu_background = ImageIO.read(new File("src/main/res/icons/menu/background/menu_background.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            mainlogo = ImageIO.read(new File("src/main/res/icons/menu/logo/head.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            info = ImageIO.read(new File("src/main/res/icons/game-panel/info.png"));
            info = resizeImage(info, 50);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads map creation-related images such as ground and grass textures.
     */
    private void mapCreate() {
        try {
            ground = ImageIO.read(new File("src/main/res/icons/mapCreate/ground.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            grass1 = ImageIO.read(new File("src/main/res/icons/mapCreate/grass1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            grass2 = ImageIO.read(new File("src/main/res/icons/mapCreate/grass2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads the map image and creates a resized minimap.
     */
    public void map() {
        try {
            map = ImageIO.read(new File("src/main/res/icons/game-panel/map.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        minimap = resizeImage(map, 150);
    }

    /**
     * Loads various game-related resources such as buttons and objects in the game.
     */
    public void game() {
        try {
            logoutButton = ImageIO.read(new File("src/main/res/icons/game-panel/logout.png"));
            logoutButton = resizeImage(logoutButton, 50);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            calender = ImageIO.read(new File("src/main/res/icons/game-panel/calendar.png"));
            calender = resizeImage(calender, 50);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            coinStack = ImageIO.read(new File("src/main/res/icons/game-panel/coin-stack.png"));
            coinStack = resizeImage(coinStack, 50);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            palmTree = ImageIO.read(new File("src/main/res/icons/game-panel/plants/palm-tree.png"));
            palmTree = resizeImage(palmTree, 140);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            baobab = ImageIO.read(new File("src/main/res/icons/game-panel/plants/baobab.png"));
            baobab = resizeImage(baobab, 180);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            pancium = ImageIO.read(new File("src/main/res/icons/game-panel/plants/grass.png"));
            pancium = resizeImage(pancium, 50);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            water = ImageIO.read(new File("src/main/res/icons/game-panel/lake.png"));
            water = resizeImage(water, 160);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            entry = ImageIO.read(new File("src/main/res/icons/game-panel/entry.png"));
            entry = resizeImage(entry, 200);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            exit = ImageIO.read(new File("src/main/res/icons/game-panel/exit.png"));
            exit = resizeImage(exit, 200);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            jeep = ImageIO.read(new File("src/main/res/icons/game-panel/jeep.png"));
            jeep = resizeImage(jeep, 80);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            jeep_back = ImageIO.read(new File("src/main/res/icons/game-panel/jeep_back.png"));
            jeep_back = resizeImage(jeep_back, 80);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads feedback-related images such as win, lose, and bad feedback icons.
     */
    private void feedBacks() {
        try {
            winFeddBack = ImageIO.read(new File("src/main/res/icons/message/win.png"));
            winFeddBack = resizeImage(winFeddBack, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            loseFeddBack = ImageIO.read(new File("src/main/res/icons/message/lose.png"));
            loseFeddBack = resizeImage(loseFeddBack, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            badFeddBack = ImageIO.read(new File("src/main/res/icons/message/bad.png"));
            badFeddBack = resizeImage(badFeddBack, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads toolbar-related images such as buttons for different objects, animals, and other items.
     */
    private void toolBar() {
        try {
            lionButton = ImageIO.read(new File("src/main/res/icons/toolkit/shop/animal/face/lion-face.png"));
            lionButton = resizeImage(lionButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Similar image loading and resizing for other buttons like leopardButton, zebraButton, etc.
    }

    /**
     * Loads animal-related images, such as lion, leopard, zebra, etc., with resizing.
     */
    public void animals() {
        try {
            lionBody = ImageIO.read(new File("src/main/res/icons/game-panel/animals/lion.png"));
            lionBody = resizeImage(lionBody, 90);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Similar image loading and resizing for other animals like leopardBody, zebraBody, etc.
    }

    /**
     * Loads person-related images such as ranger and poacher.
     */
    private void persons() {
        try {
            ranger = ImageIO.read(new File("src/main/res/icons/game-panel/ranger.png"));
            ranger = resizeImage(ranger, 100);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            poacher = ImageIO.read(new File("src/main/res/icons/game-panel/poacher.png"));
            poacher = resizeImage(poacher, 100);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Resizes a given image to the specified height while maintaining the aspect ratio.
     *
     * @param original the original {@link BufferedImage} to resize.
     * @param height the desired height for the resized image.
     * @return the resized {@link BufferedImage}.
     */
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
