package map;

import core.Resources;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageMerger {
    public static void Create(Integer[][] map, int cols, int rows) {
        int tileSize = 5;
        int width = cols * tileSize;
        int height = rows * tileSize;

        BufferedImage bigImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bigImage.createGraphics();

        try {
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    if (map[y][x] == 0) g.drawImage(Resources.Instance.ground, x * tileSize, y * tileSize, null);
                    else if (map[y][x] == 2) g.drawImage(Resources.Instance.grass1, x * tileSize, y * tileSize, null);
                    else if (map[y][x] == 3) g.drawImage(Resources.Instance.grass2, x * tileSize, y * tileSize, null);
                }
            }

            g.dispose();

            ImageIO.write(bigImage, "PNG", new File("res/main/res/icons/game-panel/map.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void newMap(int size) {
        Create(Create1.getAMap(size, size), size, size);
    }
}
