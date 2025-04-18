package map;

import core.Resources;
import entity.mobile.person.Ranger;
import entity.notmobile.Entry;
import entity.notmobile.Exit;
import entity.notmobile.Water;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import safari.DifficultyEnum;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntityCreateTest {

    @BeforeAll
    static void setup() {
        Resources.Instance.load();
        Resources.Instance.map();
    }

    @Test
    void testEntryPositioning() {
        assertEquals(Resources.Instance.entry.getWidth() / 2, EntityCreate.getEntryX(), "Entry X pozíciója nem megfelelő.");
        assertEquals(Resources.Instance.entry.getHeight() / 2, EntityCreate.getEntryY(), "Entry Y pozíciója nem megfelelő.");
    }

    @Test
    void testExitPositioning() {
        assertEquals(Resources.Instance.map.getWidth() - Resources.Instance.exit.getWidth() / 2, EntityCreate.getExitX(), "Exit X pozíciója nem megfelelő.");
        assertEquals(Resources.Instance.map.getHeight() - Resources.Instance.exit.getHeight() / 2, EntityCreate.getExitY(), "Exit Y pozíciója nem megfelelő.");
    }

    @RepeatedTest(10)
    void testRangersCreation_easy() {
        List<Ranger> rangers = EntityCreate.getRangers(DifficultyEnum.EASY);
        assertTrue(rangers.size() >= 1 && rangers.size() <= 2, "EASY nehézségnél 1-2 ranger várható.");
    }

    @RepeatedTest(10)
    void testRangersCreation_medium() {
        List<Ranger> rangers = EntityCreate.getRangers(DifficultyEnum.MEDIUM);
        assertTrue(rangers.size() >= 0 && rangers.size() <= 1, "MEDIUM nehézségnél 0-1 ranger várható.");
    }

    @RepeatedTest(10)
    void testRangersCreation_hard() {
        List<Ranger> rangers = EntityCreate.getRangers(DifficultyEnum.HARD);
        assertEquals(0, rangers.size(), "HARD nehézségnél nem szabad rangernek lennie.");
    }

    @RepeatedTest(10)
    void testWatersCreation_easy() {
        List<Water> waters = EntityCreate.getWaters(DifficultyEnum.EASY);
        assertTrue(waters.size() >= 3 && waters.size() <= 5, "EASY nehézségnél 3-5 vízforrás várható.");
        waters.forEach(this::assertWaterWithinBounds);
    }

    @RepeatedTest(10)
    void testWatersCreation_medium() {
        List<Water> waters = EntityCreate.getWaters(DifficultyEnum.MEDIUM);
        assertTrue(waters.size() >= 2 && waters.size() <= 4, "MEDIUM nehézségnél 2-4 vízforrás várható.");
        waters.forEach(this::assertWaterWithinBounds);
    }

    @RepeatedTest(10)
    void testWatersCreation_hard() {
        List<Water> waters = EntityCreate.getWaters(DifficultyEnum.HARD);
        assertTrue(waters.size() >= 1 && waters.size() <= 3, "HARD nehézségnél 1-3 vízforrás várható.");
        waters.forEach(this::assertWaterWithinBounds);
    }

    private void assertWaterWithinBounds(Water water) {
        int w = Resources.Instance.map.getWidth();
        int h = Resources.Instance.map.getHeight();
        int ww = Resources.Instance.water.getWidth() / 2;
        int wh = Resources.Instance.water.getHeight() / 2;

        assertTrue(water.getX() >= ww && water.getX() <= w - ww,
                "Water X pozíciója kívül van a határokon.");
        assertTrue(water.getY() >= wh && water.getY() <= h - wh,
                "Water Y pozíciója kívül van a határokon.");
    }
}
