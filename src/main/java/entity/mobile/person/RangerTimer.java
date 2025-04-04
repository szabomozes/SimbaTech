package entity.mobile.person;

import entity.mobile.Jeep;
import panels.CardPanel;
import safari.Prices;
import safari.Safari;
import safari.Speed;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RangerTimer {

    private Instant lastUpdate;
    private ScheduledExecutorService scheduler;
    private final Ranger ranger;
    private final Random rnd = new Random();

    public RangerTimer(Ranger ranger) {
        this.ranger = ranger;
        lastUpdate = Instant.now();
        startTimer();
    }

    private void startTimer() {
        if (scheduler != null) {
            scheduler.shutdown(); // Leállítjuk az előző időzítőt, ha létezik
        }

        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::updateRanger, 1_000_000_000, 5_000_000, TimeUnit.NANOSECONDS);
    }

    private void updateRanger() {
        try {
            Instant now = Instant.now();
            Duration elapsed = Duration.between(lastUpdate, now);

            if (elapsed.toNanos() >= Speed.Instance.speedEnum.getRangerNanoSec()) {
                lastUpdate = now;

                if (ranger.isNewPosition()) {
                    // seta a kijelolt poziba
                }


                // Frissítjük a megjelenítést
                CardPanel.Instance.repaint();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void stop() {
        if (scheduler != null) {
            scheduler.shutdown();
        }
    }
}
