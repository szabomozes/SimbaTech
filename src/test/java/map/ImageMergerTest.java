package map;

import core.Resources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImageMergerTest {

    private final String outputPath = "src/main/res/test/map.png";

    @BeforeEach
    void setup() {
        Resources.Instance.load();

        File file = new File(outputPath);
        if (file.exists()) {
            boolean deleted = file.delete();
            if (!deleted) {
                System.err.println("Nem sikerült törölni a korábbi tesztképet.");
            }
        }
    }

    @Test
    void testNewMapGeneratesImage() throws IOException {
        int size = 1000;

        ImageMerger.newMap(size, outputPath);

        File output = new File(outputPath);
        assertTrue(output.exists(), "Map image should exist after newMap call.");

        BufferedImage image = ImageIO.read(output);
        assertNotNull(image, "Generated image should be readable.");
        assertEquals(size * 5, image.getWidth(), "Image width should match map size * tile size.");
        assertEquals(size * 5, image.getHeight(), "Image height should match map size * tile size.");
    }
}