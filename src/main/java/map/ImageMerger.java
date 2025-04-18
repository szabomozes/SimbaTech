package map;

import core.Resources;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Utility class for merging map data into a single image file representing the safari terrain.
 */
public class ImageMerger {

    /**
     * Creates an image from a 2D map array by tiling ground and grass images based on map values.
     *
     * @param map  The 2D Integer array representing the map, where values indicate terrain type.
     * @param cols The number of columns in the map.
     * @param rows The number of rows in the map.
     */
    public static void Create(Integer[][] map, int cols, int rows, String outputPath) {
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

            ImageIO.write(bigImage, "PNG", new File(outputPath));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a new square map of the specified size and creates an image from it.
     *
     * @param size The width and height of the square map.
     */
    public static void newMap(int size, String outputPath) {
        Create(Create1.getAMap(size, size), size, size, outputPath);
    }
}