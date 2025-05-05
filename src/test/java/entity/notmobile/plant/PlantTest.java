package entity.notmobile.plant;

import core.Resources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class PlantTest {

    @BeforeEach
    void setUp() {
        Resources.Instance.map = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
        Resources.Instance.baobab = new BufferedImage(100, 200, BufferedImage.TYPE_INT_ARGB);
        Resources.Instance.palmTree = new BufferedImage(80, 150, BufferedImage.TYPE_INT_ARGB);
        Resources.Instance.pancium = new BufferedImage(60, 120, BufferedImage.TYPE_INT_ARGB);
    }

    @Test
    void testBaobab() {
        Baobab b = new Baobab(500, 500);
        assertEquals(100, b.getWidth(), "Baobab width should be 100");
        assertEquals(200, b.getHeight(), "Baobab height should be 200");
        assertTrue(b.isAlive(), "Baobab should be alive after initialization");
    }

    @Test
    void testPalmTree() {
        PalmTree p = new PalmTree(400, 400);
        assertEquals(80, p.getWidth(), "PalmTree width should be 80");
        assertEquals(150, p.getHeight(), "PalmTree height should be 150");
        assertTrue(p.isAlive(), "PalmTree should be alive after initialization");
    }

    @Test
    void testPancium() {
        Pancium p = new Pancium(300, 300);
        assertEquals(60, p.getWidth(), "Pancium width should be 60");
        assertEquals(120, p.getHeight(), "Pancium height should be 120");
        assertTrue(p.isAlive(), "Pancium should be alive after initialization");
    }
}
