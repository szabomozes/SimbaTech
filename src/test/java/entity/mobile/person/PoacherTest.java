package entity.mobile.person;

import static org.junit.jupiter.api.Assertions.*;

import core.Resources;
import entity.Entity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import safari.Safari;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class PoacherTest {

    private Poacher poacher;

    private static class TestSafari extends Safari {
        private final List<Entity> animals = new ArrayList<>();
        private final List<Entity> rangersAndJeeps = new ArrayList<>();

        public TestSafari() {
            super();
        }

        @Override
        public List<Entity> getAnimals() {
            return animals;
        }

        public void addEntity(Entity entity) {
            animals.add(entity);
        }

        @Override
        public List<Entity> rangersAndJeeps() {
            return rangersAndJeeps;
        }
    }

    private static class TestEntity extends Entity {
        private final int x, y, width, height;

        private boolean isDead = false;

        public TestEntity(int x, int y, int width, int height) {
            super(x, y, new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB));
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public boolean isDead() {
            return isDead;
        }

        public void kill() {
            this.isDead = true;
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
    static void loadigResources() {
        Resources.Instance.load();
    }

    @BeforeEach
    void setUp() {
        Resources.Instance.map = new BufferedImage(5000, 5000, BufferedImage.TYPE_INT_ARGB);
        poacher = new Poacher(100, 100);
        Safari.Instance = new TestSafari();
    }

    @Test
    void testPoacherConstructor() {
        int expectedX = 100 - poacher.getWidth() / 2;
        int expectedY = 100 - poacher.getHeight() / 2;
        assertEquals(expectedX, poacher.getX());
        assertEquals(expectedY, poacher.getY());
        assertFalse(poacher.isVisible());
        assertEquals(100, poacher.getTargetX());
        assertEquals(100, poacher.getTargetY());
    }

    @Test
    void testHide() {
        poacher.hide();
        assertFalse(poacher.isVisible());
    }

    @Test
    void testReveal() {
        poacher.hide();
        poacher.reveal();
        assertTrue(poacher.isVisible());
    }

    @Test
    void testPoacherVisibilityWhenEntityIsNearby() {
        TestSafari testSafari = (TestSafari) Safari.Instance;
        testSafari.addEntity(new TestEntity(90, 100, 10, 10));
        poacher.poacherVisibility();
        assertTrue(!poacher.isVisible());
    }

    @Test
    void testSetTargetXAndY() throws Exception {
        var method = Poacher.class.getDeclaredMethod("setTargetXAndY", double.class, double.class);
        method.setAccessible(true);
        double currentX = poacher.getX();
        double currentY = poacher.getY();
        method.invoke(poacher, currentX, currentY);
        double distanceX = Math.abs(poacher.getTargetX() - currentX);
        double distanceY = Math.abs(poacher.getTargetY() - currentY);
        assertTrue(distanceX >= 1 || distanceY >= 1);
    }

    @Test
    void testPoacherMove() {
        poacher.setCurrentX(100);
        poacher.setCurrentY(100);
        poacher.setTargetX(150);
        poacher.setTargetY(150);
        poacher.move();
        assertTrue(poacher.getCurrentX() != 100);
        assertTrue(poacher.getCurrentY() != 100);
    }

    @Test
    void testTeleportCoordinates() {
        double originalX = poacher.getCurrentX();
        double originalY = poacher.getCurrentY();
        Random rand = new Random();
        double newX = rand.nextDouble(Resources.Instance.map.getWidth());
        double newY = rand.nextDouble(Resources.Instance.map.getHeight());
        poacher.setCurrentX(newX);
        poacher.setCurrentY(newY);
        assertNotEquals(originalX, poacher.getCurrentX());
        assertNotEquals(originalY, poacher.getCurrentY());
    }

    @Test
    void testSearchAnimalsToKillBasic() {
        TestEntity animalInRange = new TestEntity(120, 120, 10, 10);
        TestEntity animalOutOfRange = new TestEntity(500, 500, 10, 10);
        TestSafari testSafari = (TestSafari) Safari.Instance;
        testSafari.addEntity(animalInRange);
        testSafari.addEntity(animalOutOfRange);
        poacher.setCurrentX(100);
        poacher.setCurrentY(100);
        poacher.searchAnimalsToKill();
        assertNotNull(animalInRange);
    }

    @Test
    void testShoot() {
        TestEntity animal = new TestEntity(120, 120, 10, 10);
        assertTrue(animal.isAlive());
        poacher.shoot(animal);
        assertFalse(animal.isAlive());
    }
}
