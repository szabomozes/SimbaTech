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
        super(jeep.id);
        this.jeep = jeep;
    }

    @Override
    protected void update() {
        try {
            Instant now = Instant.now();
            Duration elapsed = Duration.between(lastUpdate, now);

            if (!Safari.Instance.getPaths().isEmpty() && elapsed.toNanos() >= Speed.Instance.speedEnum.getJeepNanoSec()) {
                lastUpdate = now;
                handleJeepMovement();
                CardPanel.Instance.repaint();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handleJeepMovement() {
        if (jeep.isAvaliable()) {
            initializeJeep();
        } else if (jeep.isForward()) {
            moveJeepForward();
        } else if (!jeep.isForward() && jeep.getPassenger() > 0) {
            collectPassengerPayment();
        } else if (!jeep.isForward() && jeep.getPassenger() == 0) {
            moveJeepBackward();
        }
    }

    private void initializeJeep() {
        jeep.setAvaliable(false);
        jeep.setPassenger(rnd.nextInt(4) + 1);
        jeep.setSelectedPathIndex(rnd.nextInt(Safari.Instance.getPaths().size()));
        jeep.setForward(true);
        jeep.setPathIndex(0);
        jeep.setPath(Safari.Instance.getPaths().get(jeep.getSelectedPathIndex()).getPathCoordinations());
        jeep.setMaxPathIndex(jeep.getPath().size());
    }

    private void moveJeepForward() {
        jeep.setPathIndex(jeep.getPathIndex() + Speed.Instance.speedEnum.getJeepSteps());
        if (jeep.getPathIndex() >= jeep.getMaxPathIndex()) {
            jeep.setPathIndex(jeep.getMaxPathIndex() - 1);
            jeep.setForward(false);
        }
        updateJeepPosition();
    }

    private void collectPassengerPayment() {
        Safari.Instance.coin += (int) (Prices.getPriceByEnum(Prices.PASSENGER) * jeep.getPassenger());
        jeep.setPassenger(0);
    }

    private void moveJeepBackward() {
        jeep.setPathIndex(jeep.getPathIndex() - Speed.Instance.speedEnum.getJeepSteps());
        if (jeep.getPathIndex() < 0) {
            jeep.setPathIndex(0);
            jeep.setAvaliable(true);
        }
        updateJeepPosition();
    }

    private void updateJeepPosition() {
        jeep.setX(jeep.getPath().get(jeep.getPathIndex()).x - jeep.getWidth() / 2);
        jeep.setY(jeep.getPath().get(jeep.getPathIndex()).y - jeep.getHeight() / 2);
    }
}
