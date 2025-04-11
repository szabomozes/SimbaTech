package timer;

import panels.feedback.LoseFeedBackPanel;
import panels.feedback.WinFeedBackPanel;
import panels.game.EventPanel;
import safari.Safari;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

public class WinOrLoseTimer extends Timer {
    private EventPanel eventPanel;

    public WinOrLoseTimer(EventPanel eventPanel) {
        super(1000, null);
        this.eventPanel = eventPanel;
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("winOrLoseTimer running");
                switch (Safari.Instance.getWinOrLose()) {
                    case "win":
                        eventPanel.setFeedback(new WinFeedBackPanel());
                        stopTimer();
                        break;
                    case "lose":
                        eventPanel.setFeedback(new LoseFeedBackPanel());
                        stopTimer();
                        break;
                }
            }
        });
    }

    public void stopTimer() {
        stop();
    }
}
