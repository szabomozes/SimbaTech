package core;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Resources {
    public static Resources Instance = new Resources();

    public BufferedImage ground0;
    public BufferedImage grass2;
    public BufferedImage grass3;
    public BufferedImage currentMap;

    private Resources() {}

    public void load() {
        mapCreate();
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

    private void currentMap() throws IOException {
        currentMap = ImageIO.read(new File("res/image/map/currentMap/map.png"));
    }
}
