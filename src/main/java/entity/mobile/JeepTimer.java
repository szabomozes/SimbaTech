package entity.mobile;

import panels.CardPanel;
import safari.Prices;
import safari.Safari;
import safari.Speed;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.Duration;
import java.util.Random;

public class JeepTimer extends Timer {

    private Instant lastUpdate;

    public JeepTimer(Jeep jeep) {
        super(1, null); // 1 milliszekundumonként futtatjuk
        Random rnd = new Random();
        lastUpdate = Instant.now();
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Instant now = Instant.now();
                Duration elapsed = Duration.between(lastUpdate, now);

                if (elapsed.toMillis() >= Speed.Instance.speedEnum.getJeepNanoSec()) {
                    lastUpdate = now;
                    if (jeep.isAvaliable()) {
                        jeep.setAvaliable(false);
                        jeep.setPassenger(rnd.nextInt(4) + 1);
                        jeep.setSelectedPathIndex(rnd.nextInt(Safari.Instance.getPaths().size()));
                        jeep.setForward(true);
                        jeep.setPathIndex(0);
                        jeep.setPath(Safari.Instance.getPaths().get(jeep.getPathIndex()).getPathCoordinations());
                        jeep.setMaxPathIndex(jeep.getPath().size());
                    } else if (jeep.isForward()) {
                        jeep.setPathIndex(jeep.getPathIndex() + 10);
                        if (jeep.getPathIndex() >= jeep.getMaxPathIndex()) {
                            jeep.setPathIndex(jeep.getMaxPathIndex()-1);
                            jeep.setForward(false);
                        }
                        jeep.setX(jeep.getPath().get(jeep.getPathIndex()).x);
                        jeep.setY(jeep.getPath().get(jeep.getPathIndex()).y);
                    } else if (!jeep.isForward() && jeep.getPassenger() > 0) {
                        Safari.Instance.coin += (int) (Prices.getPriceByEnum(Prices.PASSENGER) * jeep.getPassenger());
                        jeep.setPassenger(0);
                    } else if (!jeep.isForward()) {

                    }
                    CardPanel.Instance.repaint();
                }
            }
        });
    }

    public void stopTimer() {
        stop();  // Swing Timer beépített stop() metódusa
        lastUpdate = Instant.now(); // Reseteljük az időzítőt
    }
}
