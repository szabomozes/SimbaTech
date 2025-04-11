package safari;

import core.Resources;
import entity.Entity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.*;

class GameStateCheckerTest {

    private GameStateChecker checker;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    static class MockAnimal extends Entity {
        private final String type;

        MockAnimal(int x, int y, String type) {
            super(x, y, Resources.Instance.jeep);
            this.type = type;
        }

        String getType() {
            return type;
        }
    }

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        Resources.Instance.load();
        checker = new GameStateChecker(DifficultyEnum.EASY);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void constructor_setsEasyThresholds() {
        assertEquals(50, checker.getVisitorThreshold(), "Visitor threshold should be 50 for EASY");
        assertEquals(10, checker.getHerbivoreThreshold(), "Herbivore threshold should be 10 for EASY");
        assertEquals(3, checker.getPredatorThreshold(), "Predator threshold should be 3 for EASY");
        assertEquals(500, checker.getCoinThreshold(), "Coin threshold should be 500 for EASY");
    }

    @Test
    void constructor_setsMediumThresholds() {
        checker = new GameStateChecker(DifficultyEnum.MEDIUM);
        assertEquals(80, checker.getVisitorThreshold(), "Visitor threshold should be 80 for MEDIUM");
        assertEquals(20, checker.getHerbivoreThreshold(), "Herbivore threshold should be 20 for MEDIUM");
        assertEquals(5, checker.getPredatorThreshold(), "Predator threshold should be 5 for MEDIUM");
        assertEquals(1000, checker.getCoinThreshold(), "Coin threshold should be 1000 for MEDIUM");
    }

    @Test
    void constructor_setsHardThresholds() {
        checker = new GameStateChecker(DifficultyEnum.HARD);
        assertEquals(120, checker.getVisitorThreshold(), "Visitor threshold should be 120 for HARD");
        assertEquals(30, checker.getHerbivoreThreshold(), "Herbivore threshold should be 30 for HARD");
        assertEquals(8, checker.getPredatorThreshold(), "Predator threshold should be 8 for HARD");
        assertEquals(2000, checker.getCoinThreshold(), "Coin threshold should be 2000 for HARD");
    }

    @Test
    void instantLose_returnsTrueWhenCoinsNegative() {
        List<Entity> animals = new ArrayList<>();
        animals.add(new MockAnimal(0, 0, "Herbivore"));
        assertTrue(checker.instantLose(-1, animals), "Should lose instantly with negative coins");
    }

    @Test
    void instantLose_returnsTrueWhenNoAnimals() {
        List<Entity> animals = new ArrayList<>();
        assertTrue(checker.instantLose(100, animals), "Should lose instantly with no animals");
    }

    @Test
    void instantLose_returnsFalseWhenNoLossCondition() {
        List<Entity> animals = new ArrayList<>();
        animals.add(new MockAnimal(0, 0, "Herbivore"));
        assertFalse(checker.instantLose(100, animals), "Should not lose with positive coins and animals");
    }

    @Test
    void checkWin_returnsFalseBeforeCycleEndEasy() {
        List<Entity> animals = new ArrayList<>();
        animals.add(new MockAnimal(0, 0, "Herbivore"));
        animals.add(new MockAnimal(0, 0, "Predator"));
        int date = 89;
        assertFalse(checker.checkWin(DifficultyEnum.EASY, date, animals, 500, 50),
                "Should not evaluate win/loss before cycle end for EASY");
    }

    @Test
    void checkWin_returnsTrueWhenAllThresholdsMetEasy() {
        List<Entity> animals = new ArrayList<>();
        for (int i = 0; i < 10; i++) animals.add(new MockAnimal(0, 0, "Herbivore"));
        for (int i = 0; i < 3; i++) animals.add(new MockAnimal(0, 0, "Predator"));
        int date = 90;
        assertTrue(checker.checkWin(DifficultyEnum.EASY, date, animals, 500, 50),
                "Should win when all EASY thresholds are met");
    }

    @Test
    void checkWin_returnsTrueWhenThresholdsNotMetEasy() {
        List<Entity> animals = new ArrayList<>();
        animals.add(new MockAnimal(0, 0, "Herbivore"));
        animals.add(new MockAnimal(0, 0, "Predator"));
        int date = 90;
        assertTrue(checker.checkWin(DifficultyEnum.EASY, date, animals, 100, 10),
                "Should return true (loss) when EASY thresholds not met at cycle end");
    }

    @Test
    void checkWin_returnsFalseBeforeCycleEndMedium() {
        checker = new GameStateChecker(DifficultyEnum.MEDIUM);
        List<Entity> animals = new ArrayList<>();
        animals.add(new MockAnimal(0, 0, "Herbivore"));
        animals.add(new MockAnimal(0, 0, "Predator"));
        int date = 179;
        assertFalse(checker.checkWin(DifficultyEnum.MEDIUM, date, animals, 1000, 80),
                "Should not evaluate win/loss before cycle end for MEDIUM");
    }

    @Test
    void checkWin_returnsTrueWhenAllThresholdsMetMedium() {
        checker = new GameStateChecker(DifficultyEnum.MEDIUM);
        List<Entity> animals = new ArrayList<>();
        for (int i = 0; i < 20; i++) animals.add(new MockAnimal(0, 0, "Herbivore"));
        for (int i = 0; i < 5; i++) animals.add(new MockAnimal(0, 0, "Predator"));
        int date = 180;
        assertTrue(checker.checkWin(DifficultyEnum.MEDIUM, date, animals, 1000, 80),
                "Should win when all MEDIUM thresholds are met");
    }

    @Test
    void checkWin_returnsTrueWhenThresholdsNotMetMedium() {
        checker = new GameStateChecker(DifficultyEnum.MEDIUM);
        List<Entity> animals = new ArrayList<>();
        animals.add(new MockAnimal(0, 0, "Herbivore"));
        animals.add(new MockAnimal(0, 0, "Predator"));
        int date = 180;
        assertTrue(checker.checkWin(DifficultyEnum.MEDIUM, date, animals, 500, 50),
                "Should return true (loss) when MEDIUM thresholds not met at cycle end");
    }

    @Test
    void checkWin_returnsFalseBeforeCycleEndHard() {
        checker = new GameStateChecker(DifficultyEnum.HARD);
        List<Entity> animals = new ArrayList<>();
        animals.add(new MockAnimal(0, 0, "Herbivore"));
        animals.add(new MockAnimal(0, 0, "Predator"));
        int date = 359;
        assertFalse(checker.checkWin(DifficultyEnum.HARD, date, animals, 2000, 120),
                "Should not evaluate win/loss before cycle end for HARD");
    }

    @Test
    void checkWin_returnsTrueWhenAllThresholdsMetHard() {
        checker = new GameStateChecker(DifficultyEnum.HARD);
        List<Entity> animals = new ArrayList<>();
        for (int i = 0; i < 30; i++) animals.add(new MockAnimal(0, 0, "Herbivore"));
        for (int i = 0; i < 8; i++) animals.add(new MockAnimal(0, 0, "Predator"));
        int date = 360;
        assertTrue(checker.checkWin(DifficultyEnum.HARD, date, animals, 2000, 120),
                "Should win when all HARD thresholds are met");
    }

    @Test
    void checkWin_returnsTrueWhenThresholdsNotMetHard() {
        checker = new GameStateChecker(DifficultyEnum.HARD);
        List<Entity> animals = new ArrayList<>();
        animals.add(new MockAnimal(0, 0, "Herbivore"));
        animals.add(new MockAnimal(0, 0, "Predator"));
        int date = 360;
        assertTrue(checker.checkWin(DifficultyEnum.HARD, date, animals, 1000, 50),
                "Should return true (loss) when HARD thresholds not met at cycle end");
    }

    @Test
    void setEasy_setsCorrectThresholds() throws Exception {
        Method method = GameStateChecker.class.getDeclaredMethod("setEasy");
        method.setAccessible(true);
        method.invoke(checker);
        assertEquals(50, checker.getVisitorThreshold(), "Visitor threshold should be 50");
        assertEquals(10, checker.getHerbivoreThreshold(), "Herbivore threshold should be 10");
        assertEquals(3, checker.getPredatorThreshold(), "Predator threshold should be 3");
        assertEquals(500, checker.getCoinThreshold(), "Coin threshold should be 500");
    }

    @Test
    void setMedium_setsCorrectThresholds() throws Exception {
        Method method = GameStateChecker.class.getDeclaredMethod("setMedium");
        method.setAccessible(true);
        method.invoke(checker);
        assertEquals(80, checker.getVisitorThreshold(), "Visitor threshold should be 80");
        assertEquals(20, checker.getHerbivoreThreshold(), "Herbivore threshold should be 20");
        assertEquals(5, checker.getPredatorThreshold(), "Predator threshold should be 5");
        assertEquals(1000, checker.getCoinThreshold(), "Coin threshold should be 1000");
    }

    @Test
    void setHard_setsCorrectThresholds() throws Exception {
        Method method = GameStateChecker.class.getDeclaredMethod("setHard");
        method.setAccessible(true);
        method.invoke(checker);
        assertEquals(120, checker.getVisitorThreshold(), "Visitor threshold should be 120");
        assertEquals(30, checker.getHerbivoreThreshold(), "Herbivore threshold should be 30");
        assertEquals(8, checker.getPredatorThreshold(), "Predator threshold should be 8");
        assertEquals(2000, checker.getCoinThreshold(), "Coin threshold should be 2000");
    }
}