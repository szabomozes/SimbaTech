package timer;

import entity.mobile.Jeep;
import panels.CardPanel;
import safari.Prices;
import safari.Safari;
import safari.Speed;

import java.time.Duration;
import java.time.Instant;

public class JeepTimer extends BasicTimer {

    private final Jeep jeep;

    public JeepTimer(Jeep jeep) {
        super();
        this.jeep = jeep;
    }

    @Override
    protected void update() {
        try {
            Instant now = Instant.now();
            Duration elapsed = Duration.between(lastUpdate, now);

            if (!Safari.Instance.getPaths().isEmpty()) {

                if (elapsed.toNanos() >= Speed.Instance.speedEnum.getJeepNanoSec()) {
                    lastUpdate = now;

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
                        jeep.setX(jeep.getPath().get(jeep.getPathIndex()).x - jeep.getWidth() / 2);
                        jeep.setY(jeep.getPath().get(jeep.getPathIndex()).y - jeep.getHeight() / 2);
                    } else if (!jeep.isForward() && jeep.getPassenger() > 0) {
                        Safari.Instance.coin += (int) (Prices.getPriceByEnum(Prices.PASSENGER) * jeep.getPassenger());
                        jeep.setPassenger(0);
                    } else if (!jeep.isForward() && jeep.getPassenger() == 0) {
                        jeep.setPathIndex(jeep.getPathIndex() - Speed.Instance.speedEnum.getJeepSteps());
                        if (jeep.getPathIndex() < 0) {
                            jeep.setPathIndex(0);
                            jeep.setAvaliable(true);
                        }
                        jeep.setX(jeep.getPath().get(jeep.getPathIndex()).x - jeep.getWidth() / 2);
                        jeep.setY(jeep.getPath().get(jeep.getPathIndex()).y - jeep.getHeight() / 2);
                    }

                    // Frissítjük a megjelenítést
                    CardPanel.Instance.repaint();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
