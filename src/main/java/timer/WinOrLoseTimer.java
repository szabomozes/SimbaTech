package timer;

import panels.feedback.LoseFeedBackPanel;
import panels.feedback.WinFeedBackPanel;
import panels.game.EventPanel;
import safari.Safari;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * A timer that checks the win or lose condition in the game and updates the event panel accordingly.
 * It displays feedback based on the result and stops the timer once the result is determined.
 */
public class WinOrLoseTimer extends Timer {

    private static WinOrLoseTimer instance;
    private EventPanel eventPanel;

    /**
     * Retrieves the singleton instance of the WinOrLoseTimer.
     *
     * @return the singleton instance of WinOrLoseTimer.
     */
    public static WinOrLoseTimer getInstance() {
        if (instance == null) {
            instance = new WinOrLoseTimer();
        }
        return instance;
    }

    /**
     * Private constructor for WinOrLoseTimer, initializing the timer with a 500ms delay and adding an action listener.
     */
    private WinOrLoseTimer() {
        super(500, null);
        addActionListener(this::handleTimerAction);
    }

    /**
     * Handles the action of the timer by checking the win or lose status and updating the event panel accordingly.
     *
     * @param e the action event triggered by the timer.
     */
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

    /**
     * Sets the event panel where feedback (win/lose) will be displayed.
     *
     * @param eventPanel the event panel to set.
     */
    public void setEventPanel(EventPanel eventPanel) {
        this.eventPanel = eventPanel;
    }

    /**
     * Starts the timer if it is not already running.
     */
    public void startTimer() {
        if (!isRunning()) {
            start();
        }
    }

    /**
     * Stops the timer if it is currently running.
     */
    public void stopTimer() {
        if (isRunning()) {
            stop();
        }
    }
}
