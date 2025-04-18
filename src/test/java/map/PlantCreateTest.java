package map;

import core.Resources;
import entity.Entity;
import entity.notmobile.plant.Baobab;
import entity.notmobile.plant.PalmTree;
import entity.notmobile.plant.Pancium;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import safari.DifficultyEnum;
import safari.Safari;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlantCreateTest {

    private List<TestEntity> createdEntities;
    private TestSafari testSafari;

    // Teszt-implementáció Safari.Instance-hez
    private static class TestSafari extends Safari {
        private final List<TestEntity> createdEntities;

        public TestSafari(List<TestEntity> createdEntities) {
            super();
            this.createdEntities = createdEntities;
        }

        @Override
        public void createAnEntityForFree(Class<? extends Entity> clazz, int x, int y) {
            createdEntities.add(new TestEntity(clazz, x, y));
        }
    }

    // Tesztelt entitás reprezentációja
    private static class TestEntity {
        Class<?> type;
        int x, y;

        TestEntity(Class<?> type, int x, int y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }
    }

    // Teszt erőforrások előkészítése (képek mérete)
    @BeforeEach
    public void setUp() {
        createdEntities = new ArrayList<>();
        Safari.Instance = new TestSafari(createdEntities);

        Resources.Instance.map = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        Resources.Instance.baobab = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        Resources.Instance.palmTree = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
        Resources.Instance.pancium = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
    }

    // Segédfüggvény entitásszám-ellenőrzéshez
    private void assertEntitiesInRange(Class<?> type, int min, int max) {
        long count = createdEntities.stream().filter(e -> e.type.equals(type)).count();
        assertTrue(count >= min && count < max, "Entity count not in range: " + count);
    }

    // Segédfüggvény koordináták érvényességére
    private void assertCoordinatesInBounds(Class<?> type, int width, int height) {
        for (TestEntity entity : createdEntities) {
            if (entity.type.equals(type)) {
                assertTrue(entity.x >= width / 2 && entity.x <= Resources.Instance.map.getWidth() - width / 2);
                assertTrue(entity.y >= height / 2 && entity.y <= Resources.Instance.map.getHeight() - height / 2);
            }
        }
    }

    @Test
    public void testBaobabCreationEasy() {
        PlantCreate.getBaobabs(DifficultyEnum.EASY);
        assertEntitiesInRange(Baobab.class, 2, 4);
        assertCoordinatesInBounds(Baobab.class, 50, 50);
    }

    @Test
    public void testBaobabCreationMedium() {
        PlantCreate.getBaobabs(DifficultyEnum.MEDIUM);
        assertEntitiesInRange(Baobab.class, 0, 3);
        assertCoordinatesInBounds(Baobab.class, 50, 50);
    }

    @Test
    public void testBaobabCreationHard() {
        PlantCreate.getBaobabs(DifficultyEnum.HARD);
        assertEntitiesInRange(Baobab.class, 1, 3);
        assertCoordinatesInBounds(Baobab.class, 50, 50);
    }

    @Test
    public void testPalmTreeCreationEasy() {
        PlantCreate.getPalmTrees(DifficultyEnum.EASY);
        assertEntitiesInRange(PalmTree.class, 2, 4);
        assertCoordinatesInBounds(PalmTree.class, 40, 60);
    }

    @Test
    public void testPalmTreeCreationMedium() {
        PlantCreate.getPalmTrees(DifficultyEnum.MEDIUM);
        assertEntitiesInRange(PalmTree.class, 1, 3);
        assertCoordinatesInBounds(PalmTree.class, 40, 60);
    }

    @Test
    public void testPalmTreeCreationHard() {
        PlantCreate.getPalmTrees(DifficultyEnum.HARD);
        assertEntitiesInRange(PalmTree.class, 0, 4);
        assertCoordinatesInBounds(PalmTree.class, 40, 60);
    }

    @Test
    public void testPanciumCreationEasy() {
        PlantCreate.getPanciums(DifficultyEnum.EASY);
        assertEntitiesInRange(Pancium.class, 1, 3);
        assertCoordinatesInBounds(Pancium.class, 30, 30);
    }

    @Test
    public void testPanciumCreationMedium() {
        PlantCreate.getPanciums(DifficultyEnum.MEDIUM);
        assertEntitiesInRange(Pancium.class, 0, 2);
        assertCoordinatesInBounds(Pancium.class, 30, 30);
    }

    @Test
    public void testPanciumCreationHard() {
        PlantCreate.getPanciums(DifficultyEnum.HARD);
        assertEntitiesInRange(Pancium.class, 0, 1);
        assertCoordinatesInBounds(Pancium.class, 30, 30);
    }
}