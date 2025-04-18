package map;

import core.Resources;
import entity.mobile.animal.Giraffe;
import entity.mobile.animal.Leopard;
import entity.mobile.animal.Lion;
import entity.mobile.animal.Zebra;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import safari.DifficultyEnum;
import safari.Safari;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AnimalCreateTest {

    @BeforeAll
    static void setUpAll() {
        Resources.Instance.load();
        Resources.Instance.map();
    }

    @BeforeEach
    void setUp() {
        Safari.Instance.clearAllEntities();
    }

    @Test
    void testGetLionsEasy() {
        AnimalCreate.getLions(DifficultyEnum.EASY);
        long lionCount = Safari.Instance.getAnimals().stream().filter(animal -> animal instanceof Lion).count();
        System.out.println("Lion Easy: "+lionCount);
        assertTrue(lionCount <= 1, "Az EASY szinten az oroszlánok száma nem haladhatja meg az 1-et.");
    }

    @Test
    void testGetLionsMedium() {
        AnimalCreate.getLions(DifficultyEnum.MEDIUM);
        long lionCount = Safari.Instance.getAnimals().stream().filter(animal -> animal instanceof Lion).count();
        System.out.println("Lion Medium: "+lionCount);
        assertTrue(lionCount >= 2 && lionCount <= 3, "A MEDIUM szinten az oroszlánok száma 2 és 3 között kell legyen.");
    }

    @Test
    void testGetLionsHard() {
        AnimalCreate.getLions(DifficultyEnum.HARD);
        long lionCount = Safari.Instance.getAnimals().stream().filter(animal -> animal instanceof Lion).count();
        System.out.println("Lion Hard: "+lionCount);
        assertTrue(lionCount >= 2 && lionCount <= 3, "A HARD szinten az oroszlánok száma 2 és 3 között kell legyen.");
    }

    @Test
    void testGetLeopardsEasy() {
        AnimalCreate.getLeopards(DifficultyEnum.EASY);
        long leopardCount = Safari.Instance.getAnimals().stream().filter(animal -> animal instanceof Leopard).count();
        assertTrue(leopardCount <= 1, "Az EASY szinten a leopárdok száma nem haladhatja meg az 1-et.");
    }

    @Test
    void testGetLeopardsMedium() {
        AnimalCreate.getLeopards(DifficultyEnum.MEDIUM);
        long leopardCount = Safari.Instance.getAnimals().stream().filter(animal -> animal instanceof Leopard).count();
        assertTrue(leopardCount >= 1 && leopardCount <= 2, "A MEDIUM szinten a leopárdok száma 1 és 2 között kell legyen.");
    }

    @Test
    void testGetLeopardsHard() {
        AnimalCreate.getLeopards(DifficultyEnum.HARD);
        long leopardCount = Safari.Instance.getAnimals().stream().filter(animal -> animal instanceof Leopard).count();
        assertTrue(leopardCount >= 2 && leopardCount <= 3, "A HARD szinten a leopárdok száma 2 és 3 között kell legyen.");
    }

    @Test
    void testGetZebrasEasy() {
        AnimalCreate.getZebras(DifficultyEnum.EASY);
        long zebraCount = Safari.Instance.getAnimals().stream().filter(animal -> animal instanceof Zebra).count();
        assertTrue(zebraCount >= 2 && zebraCount <= 3, "Az EASY szinten a zebrák száma 2 és 4 között kell legyen.");
    }

    @Test
    void testGetZebrasMedium() {
        AnimalCreate.getZebras(DifficultyEnum.MEDIUM);
        long zebraCount = Safari.Instance.getAnimals().stream().filter(animal -> animal instanceof Zebra).count();
        assertTrue(zebraCount >= 2 && zebraCount <= 4, "A MEDIUM szinten a zebrák száma 2 és 4 között kell legyen.");
    }

    @Test
    void testGetZebrasHard() {
        AnimalCreate.getZebras(DifficultyEnum.HARD);
        long zebraCount = Safari.Instance.getAnimals().stream().filter(animal -> animal instanceof Zebra).count();
        assertTrue(zebraCount >= 2 && zebraCount <= 3, "A HARD szinten a zebrák száma 2 és 3 között kell legyen.");
    }

    @Test
    void testGetGiraffesEasy() {
        AnimalCreate.getGiraffes(DifficultyEnum.EASY);
        long giraffeCount = Safari.Instance.getAnimals().stream().filter(animal -> animal instanceof Giraffe).count();
        assertTrue(giraffeCount >= 2 && giraffeCount <= 4, "Az EASY szinten a zsiráfok száma 2 és 5 között kell legyen.");
    }

    @Test
    void testGetGiraffesMedium() {
        AnimalCreate.getGiraffes(DifficultyEnum.MEDIUM);
        long giraffeCount = Safari.Instance.getAnimals().stream().filter(animal -> animal instanceof Giraffe).count();
        assertTrue(giraffeCount >= 1 && giraffeCount <= 3, "A MEDIUM szinten a zsiráfok száma 1 és 4 között kell legyen.");
    }

    @Test
    void testGetGiraffesHard() {
        AnimalCreate.getGiraffes(DifficultyEnum.HARD);
        long giraffeCount = Safari.Instance.getAnimals().stream().filter(animal -> animal instanceof Giraffe).count();
        assertTrue(giraffeCount >= 1 && giraffeCount <= 2, "A HARD szinten a zsiráfok száma 1 és 2 között kell legyen.");
    }
}
