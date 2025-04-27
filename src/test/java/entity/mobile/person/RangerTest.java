package entity.mobile.person;

import static org.junit.jupiter.api.Assertions.*;

import core.Resources;
import entity.Entity;
import entity.mobile.animal.Leopard;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import safari.Safari;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

class RangerTest {

    private Ranger ranger;

    private static class TestSafari extends Safari {
        private final List<Entity> rangersAndJeeps = new ArrayList<>();

        public TestSafari() {
            super();
        }

        @Override
        public List<Entity> getAnimals() {
            return new ArrayList<>();
        }

        @Override
        public List<Entity> rangersAndJeeps() {
            return rangersAndJeeps;
        }
    }

    private static class TestEntity extends Entity {
        private final int x, y, width, height;

        public TestEntity(int x, int y, int width, int height) {
            super(x, y, new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB));
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        @Override
        public int getX() {
            return x;
        }

        @Override
        public int getY() {
            return y;
        }

        @Override
        public int getWidth() {
            return width;
        }

        @Override
        public int getHeight() {
            return height;
        }
    }

    @BeforeAll
    static void loadingResources() {
        Resources.Instance.load();
    }

    @BeforeEach
    void setUp() {
        Resources.Instance.map = new BufferedImage(5000, 5000, BufferedImage.TYPE_INT_ARGB);
        ranger = new Ranger(100, 100);
        Safari.Instance = new TestSafari();
    }

    @Test
    void testRangerConstructor() {
        int expectedX = 100 - ranger.getWidth() / 2;
        int expectedY = 100 - ranger.getHeight() / 2;
        assertEquals(expectedX, ranger.getX());
        assertEquals(expectedY, ranger.getY());
    }

    @Test
    void testIsTarget() {
        assertFalse(ranger.isTarget());
        ranger.setTarget(true);
        assertTrue(ranger.isTarget());
        ranger.setTarget(false);
        assertFalse(ranger.isTarget());
    }

    @Test
    void testSetTarget() {
        assertFalse(ranger.isTarget());
        ranger.setTarget(true);
        assertTrue(ranger.isTarget());
        ranger.setTarget(false);
        assertFalse(ranger.isTarget());
    }

    @Test
    void testHandleRangerMovement() {
        TestSafari testSafari = new TestSafari();
        Safari.Instance = testSafari;
        ranger.setTarget(true);
        ranger.handleRangerMovement();
        assertTrue(ranger.isMovingToTarget());
        assertFalse(ranger.isTarget());
        ranger.setMovingToTarget(true);
        ranger.handleRangerMovement();
        assertNull(ranger.getTargetEntity());
        ranger.setNewPosition(true);
        ranger.handleRangerMovement();
        assertTrue(ranger.getX() != 0 || ranger.getY() != 0);
        ranger.setAlive(false);
        ranger.handleRangerMovement();
        assertNull(ranger.getTask());
    }

    @Test
    void testRangerMovementTowardsTarget() {
        Entity target = new Leopard(500, 500);
        ranger.setTargetEntity(target);
        ranger.setTarget(true);
        ranger.handleRangerMovement();
        assertTrue(ranger.getX() < 500);
        assertTrue(ranger.getY() < 500);
    }
}
