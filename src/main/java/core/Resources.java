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
    public BufferedImage jeep;
    public BufferedImage entry;
    public BufferedImage exit;

    private Resources() {}

    public void load() {
        menu();
        mapCreate();
        game();
        toolBar();
        animals();
        persons();
        feedBacks();
    }

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
    }

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

    public void map() {
        try {
            map = ImageIO.read(new File("src/main/res/icons/game-panel/map.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        minimap = resizeImage(map, 150);
    }

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
            water = resizeImage(water, 110);
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
            jeep = resizeImage(jeep, 100);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

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

    private void toolBar() {
        try {
            lionButton = ImageIO.read(new File("src/main/res/icons/toolkit/shop/animal/face/lion-face.png"));
            //lionButton = ImageIO.read(new File("src/main/res/icons/toolkit/shop/animal/full-body/lion-body.png"));
            lionButton = resizeImage(lionButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            leopardButton = ImageIO.read(new File("src/main/res/icons/toolkit/shop/animal/face/leopard-face.png"));
            //leopardButton = ImageIO.read(new File("src/main/res/icons/toolkit/shop/animal/full-body/leopard-body.png"));
            leopardButton = resizeImage(leopardButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            zebraButton = ImageIO.read(new File("src/main/res/icons/toolkit/shop/animal/face/zebra-face.png"));
            //zebraButton = ImageIO.read(new File("src/main/res/icons/toolkit/shop/animal/full-body/zebra-body.png"));
            zebraButton = resizeImage(zebraButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            giraffeButon = ImageIO.read(new File("src/main/res/icons/toolkit/shop/animal/face/giraffe-face.png"));
            //giraffeButon = ImageIO.read(new File("src/main/res/icons/toolkit/shop/animal/full-body/giraffe-body.png"));
            giraffeButon = resizeImage(giraffeButon, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            baobabButton = ImageIO.read(new File("src/main/res/icons/toolkit/shop/plant/baobab.png"));
            baobabButton = resizeImage(baobabButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            palmTreeButton = ImageIO.read(new File("src/main/res/icons/toolkit/shop/plant/palm-tree.png"));
            palmTreeButton = resizeImage(palmTreeButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            panciumButton = ImageIO.read(new File("src/main/res/icons/toolkit/shop/plant/pancium.png"));
            panciumButton = resizeImage(panciumButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            waterButton = ImageIO.read(new File("src/main/res/icons/toolkit/shop/object/water.png"));
            waterButton = resizeImage(waterButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            jeepButton = ImageIO.read(new File("src/main/res/icons/toolkit/shop/object/jeep.png"));
            jeepButton = resizeImage(jeepButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            rangerButton = ImageIO.read(new File("src/main/res/icons/toolkit/shop/person/ranger.png"));
            rangerButton = resizeImage(rangerButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            speedEadle = ImageIO.read(new File("src/main/res/icons/toolkit/speed/eagle.png"));
            speedEadle = resizeImage(speedEadle, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            speedHippopotamus = ImageIO.read(new File("src/main/res/icons/toolkit/speed/hippopotamus.png"));
            speedHippopotamus = resizeImage(speedHippopotamus, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            speedSnail = ImageIO.read(new File("src/main/res/icons/toolkit/speed/snail.png"));
            speedSnail = resizeImage(speedSnail, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            sellButton = ImageIO.read(new File("src/main/res/icons/toolkit/shop/other/sell.png"));
            sellButton = resizeImage(sellButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            roadTableButton = ImageIO.read(new File("src/main/res/icons/toolkit/road/road-table.png"));
            roadTableButton = resizeImage(roadTableButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            binButton = ImageIO.read(new File("src/main/res/icons/toolkit/road/bin.png"));
            binButton = resizeImage(binButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void animals() {
        try {
            lionBody = ImageIO.read(new File("src/main/res/icons/game-panel/animals/lion.png"));
            lionBody = resizeImage(lionBody, 90);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            leopardBody = ImageIO.read(new File("src/main/res/icons/game-panel/animals/leopard.png"));
            leopardBody = resizeImage(leopardBody, 70);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            zebraBody = ImageIO.read(new File("src/main/res/icons/game-panel/animals/zebra.png"));
            zebraBody = resizeImage(zebraBody, 100);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            giraffeBody = ImageIO.read(new File("src/main/res/icons/game-panel/animals/giraffe.png"));
            giraffeBody = resizeImage(giraffeBody, 140);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            saveButton = ImageIO.read(new File("src/main/res/icons/toolkit/road/build.png"));
            saveButton = resizeImage(saveButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            deleteButton = ImageIO.read(new File("src/main/res/icons/toolkit/road/bin.png"));
            deleteButton = resizeImage(deleteButton, 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            hourglass = ImageIO.read(new File("src/main/res/icons/menu/load/hourglass.png"));
            hourglass = resizeImage(hourglass, 200);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void persons() {
        try {
            ranger = ImageIO.read(new File("src/main/res/icons/game-panel/ranger.png"));
            ranger = resizeImage(ranger, 100);
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
