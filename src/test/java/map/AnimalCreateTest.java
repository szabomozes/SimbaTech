package map;

import core.Resources;
import entity.Entity;
import entity.mobile.animal.Giraffe;
import entity.mobile.animal.Leopard;
import entity.mobile.animal.Lion;
import entity.mobile.animal.Zebra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import safari.DifficultyEnum;
import safari.Safari;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalCreateTest {

    private List<TestEntity> createdEntities;

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

    private static class TestEntity {
        Class<?> type;
        int x, y;

        TestEntity(Class<?> type, int x, int y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }
    }

    @BeforeEach
    public void setUp() {
        createdEntities = new ArrayList<>();
        Safari.Instance = new TestSafari(createdEntities);

        Resources.Instance.map = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        Resources.Instance.giraffeBody = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        Resources.Instance.leopardBody = new BufferedImage(40, 60, BufferedImage.TYPE_INT_ARGB);
        Resources.Instance.lionBody = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
        Resources.Instance.zebraBody = new BufferedImage(60, 40, BufferedImage.TYPE_INT_ARGB);
    }

    private void assertEntitiesInRange(Class<?> type, int min, int max) {
        long count = createdEntities.stream().filter(e -> e.type.equals(type)).count();
        assertTrue(count >= min && count <= max, "Entity count not in range: " + count);
    }

    private void assertCoordinatesInBounds(Class<?> type, int width, int height) {
        for (TestEntity entity : createdEntities) {
            if (entity.type.equals(type)) {
                assertTrue(entity.x >= width / 2 && entity.x <= Resources.Instance.map.getWidth() - width / 2);
                assertTrue(entity.y >= height / 2 && entity.y <= Resources.Instance.map.getHeight() - height / 2);
            }
        }
    }

    @Test
    public void testLionCreationEasy() {
        AnimalCreate.getLions(DifficultyEnum.EASY);
        assertEntitiesInRange(Lion.class, 0, 1);
        assertCoordinatesInBounds(Lion.class, 30, 30);
    }

    @Test
    public void testLionCreationMedium() {
        AnimalCreate.getLions(DifficultyEnum.MEDIUM);
        assertEntitiesInRange(Lion.class, 2, 3);
        assertCoordinatesInBounds(Lion.class, 30, 30);
    }

    @Test
    public void testLionCreationHard() {
        AnimalCreate.getLions(DifficultyEnum.HARD);
        assertEntitiesInRange(Lion.class, 2, 3);
        assertCoordinatesInBounds(Lion.class, 30, 30);
    }

    @Test
    public void testLeopardCreationEasy() {
        AnimalCreate.getLeopards(DifficultyEnum.EASY);
        assertEntitiesInRange(Leopard.class, 0, 1);
        assertCoordinatesInBounds(Leopard.class, 40, 60);
    }

    @Test
    public void testLeopardCreationMedium() {
        AnimalCreate.getLeopards(DifficultyEnum.MEDIUM);
        assertEntitiesInRange(Leopard.class, 1, 2);
        assertCoordinatesInBounds(Leopard.class, 40, 60);
    }

    @Test
    public void testLeopardCreationHard() {
        AnimalCreate.getLeopards(DifficultyEnum.HARD);
        assertEntitiesInRange(Leopard.class, 2, 3);
        assertCoordinatesInBounds(Leopard.class, 40, 60);
    }

    @Test
    public void testZebraCreationEasy() {
        AnimalCreate.getZebras(DifficultyEnum.EASY);
        assertEntitiesInRange(Zebra.class, 2, 4);
        assertCoordinatesInBounds(Zebra.class, 60, 40);
    }

    @Test
    public void testZebraCreationMedium() {
        AnimalCreate.getZebras(DifficultyEnum.MEDIUM);
        assertEntitiesInRange(Zebra.class, 2, 4);
        assertCoordinatesInBounds(Zebra.class, 60, 40);
    }

    @Test
    public void testZebraCreationHard() {
        AnimalCreate.getZebras(DifficultyEnum.HARD);
        assertEntitiesInRange(Zebra.class, 2, 3);
        assertCoordinatesInBounds(Zebra.class, 60, 40);
    }

    @Test
    public void testGiraffeCreationEasy() {
        AnimalCreate.getGiraffes(DifficultyEnum.EASY);
        assertEntitiesInRange(Giraffe.class, 2, 4);
        assertCoordinatesInBounds(Giraffe.class, 50, 50);
    }

    @Test
    public void testGiraffeCreationMedium() {
        AnimalCreate.getGiraffes(DifficultyEnum.MEDIUM);
        assertEntitiesInRange(Giraffe.class, 1, 3);
        assertCoordinatesInBounds(Giraffe.class, 50, 50);
    }

    @Test
    public void testGiraffeCreationHard() {
        AnimalCreate.getGiraffes(DifficultyEnum.HARD);
        assertEntitiesInRange(Giraffe.class, 1, 2);
        assertCoordinatesInBounds(Giraffe.class, 50, 50);
    }
}
