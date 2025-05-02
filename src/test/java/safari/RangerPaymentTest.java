package safari;

import core.Resources;
import entity.Entity;
import entity.mobile.animal.Leopard;
import entity.mobile.animal.Lion;
import entity.mobile.person.Ranger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import panels.game.Calendar;

import java.awt.image.BufferedImage;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RangerPaymentTest {

    private Safari safari;
    private Ranger ranger1;
    private Ranger ranger2;

    @BeforeAll
    static void loadingResources() {
        Resources.Instance.load();
    }

    @BeforeEach
    void setUp() {
        Resources.Instance.map = new BufferedImage(5000, 5000, BufferedImage.TYPE_INT_ARGB);
        safari = Safari.Instance;
        safari.reset(DifficultyEnum.EASY);

        ranger1 = new Ranger(0, 100);
        ranger2 = new Ranger(150, 50);

        safari.getRangers().clear();
        safari.getRangerJoinDates().clear();

        safari.getRangers().addAll(List.of(ranger1, ranger2));
        safari.getRangerJoinDates().put(ranger1, 1);
        safari.getRangerJoinDates().put(ranger2, 0);
        Safari.Instance.coin = 50;

    }

    @AfterEach
    void clear() {
        safari.getRangerJoinDates().clear();
    }

    @Test
    void testPayRangersByServiceTime_FirstDay() {
        RangerPayment.Instance.payRangersByServiceTime();
        assertEquals(44, safari.coin);
    }

    @Test
    void testPayRangersByServiceTime_Exactly30Days() {
        Calendar.Instance.setDate(30);
        safari.getRangerJoinDates().put(ranger2, 0);
        RangerPayment.Instance.payRangersByServiceTime();
        assertEquals(44, safari.coin);
    }

    @Test
    void testPayForKilledLion() {
        RangerPayment.Instance.payForKilledEntity(new Lion(0, 0));
        assertEquals(53, safari.coin); // +3 coin
    }

    @Test
    void testPayForKilledLeopard() {
        RangerPayment.Instance.payForKilledEntity(new Leopard(0, 0));
        assertEquals(52, safari.coin); // +2 coin
    }

    @Test
    void testPayForUnsupportedEntity() {
        try {
            Resources.Instance.water = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        } catch (Exception e) {
            System.err.println("Nem sikerült betölteni a víz képét.");
        }
        Entity unknownEntity = new Entity(0, 0, Resources.Instance.water) {};
        RangerPayment.Instance.payForKilledEntity(unknownEntity);
        assertEquals(50, safari.coin); // Nincs változás
    }
}
