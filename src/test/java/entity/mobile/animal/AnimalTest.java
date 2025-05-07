package entity.mobile.animal;

import core.Resources;
import entity.Entity;
import entity.notmobile.Water;
import entity.notmobile.plant.PalmTree;
import entity.notmobile.plant.Pancium;
import entity.notmobile.plant.Plant;
import map.Coordinate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entity.mobile.animal.Giraffe;
import safari.Safari;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
class AnimalTest {

    private Giraffe giraffe;

    @BeforeAll
    static void loadingResources() {
        Resources.Instance.load();
    }

    @BeforeEach
    void setUp() {
        Resources.Instance.map = new BufferedImage(5000, 5000, BufferedImage.TYPE_INT_ARGB);
        giraffe = new Giraffe(100, 100);
        Safari.Instance.coin = 100000;
    }

    @Test
    void searchClosestWater() {
        Water near = new Water(110,110);
        Water far  = new Water(180,180);
        Safari.Instance.getWaters().clear();

        Safari.Instance.getWaters().addAll(Arrays.asList(near, far));
        giraffe.watersID.clear();
        giraffe.watersID.addAll(Arrays.asList(near.id, far.id));

        Water result = giraffe.searchClosestWater();
        assertNotNull(result);
        assertEquals(near, result);
    }

    @Test
    void getClosestWater() {
        Water w1 = new Water(120,120);
        Water w2 = new Water(200,200);
        List<Water> list = Arrays.asList(w1, w2);
        Water closest = giraffe.getClosestWater(list);
        assertEquals(w1, closest);
    }

    @Test
    void distanceTo() {
        Giraffe giraffe = new Giraffe(100, 100);
        double d = giraffe.distanceTo(0, 0, 3, 4);
        assertEquals(5.0, d, 1e-6);
    }

    @Test
    void searchClosestPlant() {
        Safari.Instance.clearAllEntities();

        Safari.Instance.shopping = "palmtree";
        Safari.Instance.placeSomething(110, 110);
        Plant expected = Safari.Instance.getPlants().getFirst();

        Safari.Instance.shopping = "pancium";
        Safari.Instance.placeSomething(180, 180);

        System.out.println(Safari.Instance.getPlants());

        giraffe.plantsID.clear();
        giraffe.plantsID.addAll(
                Safari.Instance.getPlants().stream()
                        .map(p -> p.id)
                        .toList()
        );
        System.out.println(giraffe.plantsID);

        Plant result = giraffe.searchClosestPlant();
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void getClosestPlant() {
        Plant p1 = new Pancium(120,120);
        Plant p2 = new PalmTree(200,200);
        List<Plant> list = Arrays.asList(p1, p2);
        Plant closest = giraffe.getClosestPlant(list);
        assertEquals(p1, closest);
    }

    @Test
    void justMove() {
        Safari.Instance.clearAllEntities();
        int x = giraffe.getX();
        int y = giraffe.getY();
        giraffe.justMove(10);
        assertNotEquals(x, giraffe.getX());
        assertNotEquals(y, giraffe.getY());
    }

    @Test
    void handleThirst() {
        giraffe.setAlive(true);
        giraffe.movingForDrink = false;
        giraffe.scheduledFutureCoordinatesForDrink = null;

        giraffe.handleThirst();

        assertFalse(giraffe.movingForEat);
        assertNull(giraffe.scheduledFutureCoordinatesForEat);
    }

    @Test
    void moveToDrink() {
        giraffe.coordinatesForDrink = new LinkedList<>();
        giraffe.movingForDrink = true;
        giraffe.thirst = 42;

        giraffe.moveToDrink(3);

        assertFalse(giraffe.movingForDrink);
        assertEquals(200, giraffe.thirst);

        giraffe.coordinatesForDrink.add(new Coordinate(1, 1));
        giraffe.coordinatesForDrink.add(new Coordinate(2, 2));
        giraffe.coordinatesForDrink.add(new Coordinate(3, 3));
        giraffe.setCoordinate(0, 0);

        giraffe.moveToDrink(2);

        assertEquals(2, giraffe.getX());
        assertEquals(2, giraffe.getY());
        assertEquals(1, giraffe.coordinatesForDrink.size());
    }

    @Test
    void updateThirstAndHunger() {
        giraffe.setHunger(50);
        giraffe.setThirst(40);
        giraffe.updateThirstAndHunger(10, 5);
        assertTrue(giraffe.getHunger() < 50);
        assertTrue(giraffe.getThirst() < 40);

        giraffe.setHunger(1);
        giraffe.setThirst(1);
        giraffe.updateThirstAndHunger(5,5);
        assertFalse(giraffe.isAlive());
    }

    @Test
    void overlapsWaterArea() {
        Water w = new Water(150,150);
        Safari.Instance.getWaters().clear();
        Safari.Instance.getWaters().add(w);

        assertTrue(giraffe.overlapsWaterArea(155,155));

        assertFalse(giraffe.overlapsWaterArea(300,300));

    }

    @Test
    void getClosestHerbivorous() {
        Safari.Instance.clearAllEntities();
        Safari.Instance.shopping = "giraffe";
        Safari.Instance.placeSomething(100, 100);
        Animal expected = Safari.Instance.getHerbivorous().getFirst();

        Safari.Instance.shopping = "zebra";
        Safari.Instance.placeSomething(300, 300);

        Animal predator = new Lion(90, 90);
        Animal closest = predator.getClosestHerbivorous();

        assertEquals(expected, closest);
    }

    @Test
    void handleHungerHerbivorous() {
        Safari.Instance.clearAllEntities();
        giraffe.setAlive(true);
        giraffe.movingForEat = false;
        giraffe.scheduledFutureCoordinatesForEat = null;

        Safari.Instance.shopping = "palmtree";
        Safari.Instance.placeSomething(10, 10);
        Plant plant = Safari.Instance.getPlants().getFirst();

        giraffe.handleHungerHerbivorous();

        assertEquals(plant, giraffe.targetPlant);
        assertFalse(giraffe.movingForDrink);
        assertNull(giraffe.scheduledFutureCoordinatesForDrink);
    }

    @Test
    void moveToEatHerbivorous() {
        giraffe.targetPlant = new Pancium(100, 100);
        giraffe.coordinatesForEat.clear();
        giraffe.movingForEat = true;
        giraffe.hunger = 50;

        giraffe.moveToEatHerbivorous(3);

        assertFalse(giraffe.movingForEat);
        assertEquals(200, giraffe.hunger);
    }

    @Test
    void overlaps() {
        assertTrue(giraffe.overlaps(10, 20, 15, 25));
        assertFalse(giraffe.overlaps(10, 20, 25, 30));
    }

    @Test
    void lessAvgRangeLimit() {
        Safari.Instance.clearAllEntities();
        Safari.Instance.shopping = "giraffe";
        Safari.Instance.placeSomething(200, 200);
        Entity giraffe2 = Safari.Instance.getAnimals().getFirst();
        boolean result = giraffe.lessAvgRangeLimit();

        assertTrue(result);

        giraffe.setCoordinate(2000, 2000);
        result = giraffe.lessAvgRangeLimit();

        assertFalse(result);
    }

    @Test
    void moveToTheAvgRange() {
        Safari.Instance.clearAllEntities();
        giraffe.setCoordinate(5, 5);

        giraffe.moveToTheAvgRange(3);

        assertEquals(8, giraffe.getX());
        assertEquals(8, giraffe.getY());
    }

    @Test
    void handleHungerCarnivorous() {
        Safari.Instance.clearAllEntities();
        Lion carnivore = new Lion(150, 150);
        carnivore.target = null;
        Safari.Instance.shopping = "giraffe";
        Safari.Instance.placeSomething(100, 100);
        Animal expected = Safari.Instance.getHerbivorous().getFirst();

        carnivore.handleHungerCarnivorous();

        assertEquals(expected, carnivore.target);
        assertTrue(carnivore.movingForEat);
    }

    @Test
    void moveToEatCarnivorous() {
        Lion carnivore = new Lion(0, 0);
        giraffe.setCoordinate(15, 0);
        carnivore.target = giraffe;
        carnivore.movingForEat = true;
        carnivore.hunger = 0;
        carnivore.moveToEatCarnivorous(15);

        assertNull(carnivore.target);
        assertFalse(carnivore.movingForEat);
        assertEquals(200, carnivore.hunger);
        assertFalse(giraffe.isAlive());
    }
}