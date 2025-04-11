package entity.notmobile;

import core.Resources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class ObjectsTest {

    @BeforeEach
    void setUp() {
        Resources.Instance.map = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
        Resources.Instance.entry = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
        Resources.Instance.exit = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        Resources.Instance.water = new BufferedImage(60, 60, BufferedImage.TYPE_INT_ARGB);
    }

    @Test
    void testEntry() {
        Entry e = new Entry(100, 100);
        assertEquals(40, e.getWidth(), "Entry width should be 40");
        assertEquals(40, e.getHeight(), "Entry height should be 40");
        assertTrue(e.isAlive(), "Entry should be alive after initialization");
    }

    @Test
    void testExit() {
        Exit e = new Exit(200, 200);
        assertEquals(50, e.getWidth(), "Exit width should be 50");
        assertEquals(50, e.getHeight(), "Exit height should be 50");
        assertTrue(e.isAlive(), "Exit should be alive after initialization");
    }

    @Test
    void testWater() {
        Water w = new Water(300, 300);
        assertEquals(60, w.getWidth(), "Water width should be 60");
        assertEquals(60, w.getHeight(), "Water height should be 60");
        assertTrue(w.isAlive(), "Water should be alive after initialization");
    }
}
