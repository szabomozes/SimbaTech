package entity.mobile;

import static org.junit.jupiter.api.Assertions.*;
import core.Resources;
import map.Coordinate;
import safari.Safari;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.awt.image.BufferedImage;
import java.util.List;

class JeepTest {

    private Jeep jeep;
    public List<Coordinate> path;


    private static class TestSafari extends Safari {
        public TestSafari() {
            super();
        }
    }

    @BeforeAll
    static void loadResources() {
        Resources.Instance.load();
    }

    @BeforeEach
    void setUp() {
        Resources.Instance.map = new BufferedImage(5000, 5000, BufferedImage.TYPE_INT_ARGB);
        Safari.Instance = new TestSafari();
        jeep = new Jeep(100, 100);
    }

    @Test
    void testJeepInitialization() {
        assertNotNull(jeep);
        assertTrue(jeep.isAvaliable());
        assertEquals(0, jeep.getPassenger());
    }
}
