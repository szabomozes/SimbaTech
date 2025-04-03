package entity.mobile;

import panels.CardPanel;
import safari.Prices;
import safari.Safari;
import safari.Speed;
import safari.SpeedEnum;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JeepTimerPlus {

    private Instant lastUpdate;
    private ScheduledExecutorService scheduler;
    private final Jeep jeep;
    private final Random rnd = new Random();

    public JeepTimerPlus(Jeep jeep) {
        this.jeep = jeep;
        lastUpdate = Instant.now();
        startTimer();
    }

    private void startTimer() {
        if (scheduler != null) {
            scheduler.shutdown(); // Leállítjuk az előző időzítőt, ha létezik
        }

        scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(this::updateJeep, 0, 200_000, TimeUnit.NANOSECONDS);
    }

    private void updateJeep() {
        Instant now = Instant.now();
        Duration elapsed = Duration.between(lastUpdate, now);
        if (!Safari.Instance.getPaths().isEmpty()) {
            // Ellenőrizzük, hogy eltelt-e a szükséges idő
            if (elapsed.toNanos() >= Speed.Instance.speedEnum.getJeepNanoSec()) {
                lastUpdate = now;  // frissítjük az időpontot

                if (jeep.isAvaliable()) {
                    jeep.setAvaliable(false);
                    jeep.setPassenger(rnd.nextInt(4) + 1);
                    jeep.setSelectedPathIndex(rnd.nextInt(Safari.Instance.getPaths().size()));
                    jeep.setForward(true);
                    jeep.setPathIndex(0);
                    jeep.setPath(Safari.Instance.getPaths().get(jeep.getSelectedPathIndex()).getPathCoordinations());
                    jeep.setMaxPathIndex(jeep.getPath().size());
                } else if (jeep.isForward()) {
                    jeep.setPathIndex(jeep.getPathIndex() + Speed.Instance.speedEnum.getJeepSteps());
                    if (jeep.getPathIndex() >= jeep.getMaxPathIndex()) {
                        jeep.setPathIndex(jeep.getMaxPathIndex() - 1);
                        jeep.setForward(false);
                    }
                    jeep.setX(jeep.getPath().get(jeep.getPathIndex()).x);
                    jeep.setY(jeep.getPath().get(jeep.getPathIndex()).y);
                } else if (!jeep.isForward() && jeep.getPassenger() > 0) {
                    Safari.Instance.coin += (int) (Prices.getPriceByEnum(Prices.PASSENGER) * jeep.getPassenger());
                    jeep.setPassenger(0);
                } else if (!jeep.isForward()) {
                    jeep.setPathIndex(jeep.getPathIndex() - Speed.Instance.speedEnum.getJeepSteps());
                    if (jeep.getPathIndex() <= 0) {
                        jeep.setPathIndex(0);
                        jeep.setAvaliable(true);
                    }
                    jeep.setX(jeep.getPath().get(jeep.getPathIndex()).x);
                    jeep.setY(jeep.getPath().get(jeep.getPathIndex()).y);
                }

                // Frissítjük a megjelenítést
                CardPanel.Instance.repaint();
            }
        }
    }

    public void updateSpeed() {
        startTimer(); // Újraindítja az időzítőt a friss sebességgel
    }

    public void stop() {
        if (scheduler != null) {
            scheduler.shutdown();
        }
    }
}
