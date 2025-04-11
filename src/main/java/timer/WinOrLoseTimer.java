package timer;

import panels.feedback.LoseFeedBackPanel;
import panels.feedback.WinFeedBackPanel;
import panels.game.EventPanel;
import safari.Safari;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class WinOrLoseTimer extends Timer {

    private static WinOrLoseTimer instance;
    private EventPanel eventPanel;

    public static WinOrLoseTimer getInstance() {
        if (instance == null) {
            instance = new WinOrLoseTimer();
        }
        return instance;
    }

    private WinOrLoseTimer() {
        super(500, null);
        addActionListener(this::handleTimerAction);
    }

    private void handleTimerAction(ActionEvent e) {
        if (eventPanel != null) {
            String result = Safari.Instance.getWinOrLose();
            switch (result) {
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
    }

    public void setEventPanel(EventPanel eventPanel) {
        this.eventPanel = eventPanel;
    }

    public void startTimer() {
        if (!isRunning()) {
            start();
        }
    }

    public void stopTimer() {
        if (isRunning()) {
            stop();
        }
    }
}