package safari;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.Duration;

public class DateTimer extends Timer {

    private Instant lastUpdate;

    public DateTimer() {
        super(1000, null);
        lastUpdate = Instant.now();
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Instant now = Instant.now();
                Duration elapsed = Duration.between(lastUpdate, now);

                if (elapsed.getSeconds() >= Speed.Instance.speedEnum.getDateSec()) {
                    Safari.Instance.updateDate();
                    lastUpdate = now;
                }
            }
        });
    }

    public void stopTimer() {
        stop();  // Swing Timer beépített stop() metódusa
        lastUpdate = Instant.now(); // Reseteljük az időzítőt
    }

}
