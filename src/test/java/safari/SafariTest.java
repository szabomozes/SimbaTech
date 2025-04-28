package safari;

import static org.junit.jupiter.api.Assertions.*;

import core.Resources;
import entity.Entity;
import entity.mobile.animal.Animal;
import entity.mobile.animal.Giraffe;
import entity.mobile.animal.Lion;
import entity.mobile.animal.Zebra;
import entity.mobile.person.Poacher;
import entity.mobile.person.Ranger;
import entity.notmobile.plant.Plant;
import map.Coordinate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import road.Path;

import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SafariTest {

    private Safari safari;

    @BeforeAll
    static void loadResources() {
        Resources.Instance.load();
    }

    @BeforeEach
    void setUp() {
        Resources.Instance.map = new BufferedImage(5000, 5000, BufferedImage.TYPE_INT_ARGB);
        safari = Safari.Instance;
    }

    @Test
    void testSafariConstructorInitialization() throws NoSuchFieldException, IllegalAccessException {
        assertNotNull(safari);

        Field dateTimerField = Safari.class.getDeclaredField("dateTimer");
        dateTimerField.setAccessible(true);
        Object dateTimer = dateTimerField.get(safari);
        assertNotNull(dateTimer);

        Field passengersField = Safari.class.getDeclaredField("passengers");
        passengersField.setAccessible(true);
        int passengers = (int) passengersField.get(safari);
        assertEquals(0, passengers);
    }

    @Test
    void testCreateAnEntityForFree() throws NoSuchFieldException, IllegalAccessException {
        safari.createAnEntityForFree(Lion.class, 5, 10);

        Field animalsField = Safari.class.getDeclaredField("animals");
        animalsField.setAccessible(true);
        List<Entity> animals = (List<Entity>) animalsField.get(safari);

        assertFalse(animals.isEmpty());
        assertTrue(animals.get(animals.size() - 1) instanceof Lion);
    }

    @Test
    void testCreateIteration() throws NoSuchFieldException, IllegalAccessException {
        Coordinate coordinate = new Coordinate(3, 7);
        int numberOfLions = 0;

        Field animalsField = Safari.class.getDeclaredField("animals");
        animalsField.setAccessible(true);
        List<Entity> animals = (List<Entity>) animalsField.get(safari);
        animals.clear();

        safari.createIteration(Lion.class, numberOfLions, coordinate);

        long lionCount = animals.stream().filter(e -> e instanceof Lion).count();
        assertEquals(numberOfLions, lionCount);
        assertFalse(!animals.isEmpty());
    }

    @Test
    void testBornAnimals() throws NoSuchFieldException, IllegalAccessException {
        Field animalsField = Safari.class.getDeclaredField("animals");
        animalsField.setAccessible(true);
        List<Entity> animals = (List<Entity>) animalsField.get(safari);
        animals.clear();

        for (int i = 0; i < 20; i++) {
            animals.add(new Lion(i, i));
        }

        int initialLions = (int) animals.stream().filter(e -> e instanceof Lion).count();

        safari.bornAnimals();

        long finalLions = animals.stream().filter(e -> e instanceof Lion).count();

    }


    @Test
    void testAvgCoordinate() {
        safari.createAnEntityForFree(Lion.class, 0, 0);

        Coordinate avgCoord = safari.avgCoordinateOf(Lion.class);

        assertNotNull(avgCoord);
    }

    @Test
    void testGetHerbivorous() {
        safari.createAnEntityForFree(Giraffe.class, 1, 1);
        safari.createAnEntityForFree(Zebra.class, 2, 2);
        safari.createAnEntityForFree(Lion.class, 3, 3);

        List<Animal> herbivores = safari.getHerbivorous();


        assertTrue(herbivores.stream().allMatch(a -> a instanceof Giraffe || a instanceof Zebra));
    }


    @Test
    void testGetPlantsByIntegerWithEmptyList() {
        List<Plant> selectedPlants = safari.getPlantsByInteger(List.of(1));

        assertTrue(selectedPlants.isEmpty());
    }

    @Test
    void testGetRangerJoinDates() {
        Map<Ranger, Integer> joinDates = safari.getRangerJoinDates();

        assertNotNull(joinDates);
        assertTrue(joinDates.isEmpty());
    }

    @Test
    void testSaveARoad() throws NoSuchFieldException, IllegalAccessException {
        Safari safari = new Safari();

        List<Path> tempPaths = new ArrayList<>();
        tempPaths.add(new Path(0, 0));

        Field tempPathsField = Safari.class.getDeclaredField("tempPaths");
        tempPathsField.setAccessible(true);
        tempPathsField.set(safari, tempPaths);

        int x = 1;
        int y = 1;

        boolean result = safari.saveARoad(x, y);

        assertTrue(result);
    }

    @Test
    void testAddAPathToPaths() {
        Safari safari = new Safari();

        Path path = new Path(0, 0);

        safari.addAPathToPaths(path);

        try {
            Field pathsField = Safari.class.getDeclaredField("paths");
            pathsField.setAccessible(true);

            List<Path> paths = (List<Path>) pathsField.get(safari);

            assertEquals(1, paths.size());
            assertEquals(path, paths.get(0));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Field access failed: " + e.getMessage());
        }
    }

    @Test
    void testGetAllEntitiesWithSorted() {
        Safari safari = new Safari();

        List<Entity> sortedEntities = safari.getAllEntitiesWithSorted();

        assertNotNull(sortedEntities);
        assertTrue(sortedEntities.size() == 0);

        for (int i = 1; i < sortedEntities.size(); i++) {
            int currentY = sortedEntities.get(i).getY() + sortedEntities.get(i).getHeight();
            int previousY = sortedEntities.get(i - 1).getY() + sortedEntities.get(i - 1).getHeight();
            assertTrue(currentY >= previousY);
        }
    }

    @Test
    void testGetAllEntities() {
        Safari safari = new Safari();

        List<Entity> allEntities = safari.getAllEntities();

        assertNotNull(allEntities);
        assertTrue(allEntities.size() == 0);

        assertTrue(allEntities.containsAll(safari.getAnimals()));
        assertTrue(allEntities.containsAll(safari.getPlants()));
        assertTrue(allEntities.containsAll(safari.getWaters()));
        assertTrue(allEntities.containsAll(safari.getRangers()));
    }

    @Test
    void testPlacePoachers() {
        Safari safari = new Safari();

        safari.placePoachers(3);

        assertEquals(3, safari.getPoachers().size());

        safari.getPoachers().forEach(poacher -> {
            assertNotNull(poacher);
            assertTrue(poacher.getX() >= 0 && poacher.getX() < Resources.Instance.map.getWidth());
            assertTrue(poacher.getY() >= 0 && poacher.getY() < Resources.Instance.map.getHeight());
        });
    }

    @Test
    public void testBuySomething() {
        Safari safari = new Safari();
        safari.coin = 1000;

        safari.buySomething("lion");
        assertEquals("lion", safari.shopping);

        safari.coin = 100;
        safari.buySomething("lion");
        assertEquals("lion", safari.shopping);
    }

    @Test
    public void testUpdateDate() {
        Safari safari = new Safari();

        safari.coin = 1000;

        safari.updateDate();

        assertNotNull(safari.getWinOrLose());
        assertFalse(safari.getWinOrLose().equals("win"));
        assertFalse(safari.getWinOrLose().equals("lose"));
    }

    @Test
    public void testAddPassengers() {
        Safari safari = new Safari();

        int initialCount = safari.getPassengers();

        safari.addPassengers(5);

        assertEquals(initialCount + 5, safari.getPassengers());
    }
}
