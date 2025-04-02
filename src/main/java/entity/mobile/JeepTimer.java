package entity.mobile;

import safari.Safari;
import safari.Speed;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.Duration;

public class JeepTimer extends Timer {

    private Instant lastUpdate;

    public JeepTimer() {
        super(1, null); // 10 milliszekundumonként futtatjuk
        lastUpdate = Instant.now();
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Instant now = Instant.now();
                Duration elapsed = Duration.between(lastUpdate, now);

                if (elapsed.toMillis() >= Speed.Instance.speedEnum.getJeepSec() * 1000) {
                    lastUpdate = now;
                    // mozgás
                }
            }
        });
    }

    public void stopTimer() {
        stop();  // Swing Timer beépített stop() metódusa
        lastUpdate = Instant.now(); // Reseteljük az időzítőt
    }
}
