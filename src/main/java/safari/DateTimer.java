package safari;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.Duration;

/**
 * A custom Timer class that updates the safari date based on elapsed time and a configurable speed setting.
 */
public class DateTimer extends Timer {

    private Instant lastUpdate;

    /**
     * Constructs a DateTimer with a 1-second interval, tracking elapsed time to update the safari date.
     */
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

    /**
     * Stops the timer and resets the last update time to the current moment.
     */
    public void stopTimer() {
        stop();  // Swing Timer's built-in stop method
        lastUpdate = Instant.now(); // Reset the timer
    }
}