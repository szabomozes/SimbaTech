package entity.mobile;

import core.Resources;
import entity.mobile.Jeep;
import map.Coordinate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import road.Path;
import safari.Safari;

import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JeepTest {

    private Jeep jeep;


    @BeforeEach
    void setUp() {
        Resources.Instance.map = new BufferedImage(5000, 5000, BufferedImage.TYPE_INT_ARGB);
        jeep = new Jeep(100, 200);
    }

    @BeforeAll
    static void loadResources() {
        Resources.Instance.load();
    }


    @Test
    void testInitialValues() {
        assertEquals(0, jeep.getPassenger());
        assertTrue(jeep.isAvaliable());
        assertTrue(jeep.isForward());
        assertEquals(0, jeep.getPathIndex());
        assertEquals(0, jeep.getMaxPathIndex());
        assertNotNull(jeep.getPath());
    }

    @Test
    void testSetAndGetPassenger() {
        jeep.setPassenger(3);
        assertEquals(3, jeep.getPassenger());
    }

    @Test
    void testSetAndGetAvaliable() {
        jeep.setAvaliable(false);
        assertFalse(jeep.isAvaliable());
    }

    @Test
    void testSetAndGetForward() {
        jeep.setForward(false);
        assertFalse(jeep.isForward());
    }

    @Test
    void testSetAndGetPathIndex() {
        jeep.setPathIndex(5);
        assertEquals(5, jeep.getPathIndex());
    }

    @Test
    void testSetAndGetMaxPathIndex() {
        jeep.setMaxPathIndex(10);
        assertEquals(10, jeep.getMaxPathIndex());
    }

    @Test
    void testSetAndGetPath() {
        List<Coordinate> path = new ArrayList<>();
        path.add(new Coordinate(10, 20));
        path.add(new Coordinate(30, 40));
        jeep.setPath(path);
        assertEquals(2, jeep.getPath().size());
        assertEquals(10, jeep.getPath().get(0).x);
        assertEquals(40, jeep.getPath().get(1).y);
    }

    @Test
    void testSetAndGetSelectedPathIndex() {
        jeep.setSelectedPathIndex(2);
        assertEquals(2, jeep.getSelectedPathIndex());
    }

    @Test
    void testHandleJeepMovement_basicAliveAvailable() throws Exception {
        Field aliveField = null;
        Class<?> clazz = jeep.getClass();
        while (clazz != null) {
            try {
                aliveField = clazz.getDeclaredField("alive");
                break;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }

        if (aliveField == null) {
            throw new RuntimeException("Field 'alive' not found!");
        }

        aliveField.setAccessible(true);
        aliveField.set(jeep, true);

        jeep.setAvaliable(true);
        jeep.handleJeepMovement();

        assertTrue(jeep.isAvaliable());
        assertFalse(jeep.getPassenger() >= 1 && jeep.getPassenger() <= 4);
    }


}
