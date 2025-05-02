package safari;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpeedTest {

    private Speed speed;

    @BeforeEach
    public void setUp() {
        speed = Speed.Instance; // Mivel Singleton, az Instance a már meglévő objektumot adja vissza
    }

    @Test
    public void testInitialSpeedIsSnail() {
        // Ellenőrizzük, hogy az alapértelmezett sebesség SNAIL
        assertEquals(SpeedEnum.SNAIL, speed.getCurrentSpeedEnum(), "Inicializálz Speed SNAIL kellene legyen.");
    }

    @Test
    public void testSpeedChange() {
        // Módosítjuk a sebességet és ellenőrizzük
        speed.speedEnum = SpeedEnum.EAGLE;
        assertEquals(SpeedEnum.EAGLE, speed.getCurrentSpeedEnum(), "Speed osztálynak EAGLE-nek kellene lennie.");
    }

    @Test
    public void testResetSpeed() {
        // Módosítjuk a sebességet, majd visszaállítjuk az alapértelmezett értékre
        speed.speedEnum = SpeedEnum.EAGLE;
        speed.reset();
        assertEquals(SpeedEnum.SNAIL, speed.getCurrentSpeedEnum(), "Speed osztálynak vissza kellene állnia SNAIL-re.");
    }

    @Test
    public void testSingletonInstance() {
        // Ellenőrizzük, hogy a Speed osztály valóban singleton
        Speed anotherSpeedInstance = Speed.Instance;
        assertSame(speed, anotherSpeedInstance, "A Speed osztálynak szingletonnak kell lennie, ugyanazt az példányt kell visszaadnia.");
    }
}
