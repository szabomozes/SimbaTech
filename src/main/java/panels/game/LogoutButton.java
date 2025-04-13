package panels.game;

import core.Resources;
import safari.Safari;
import panels.CardPanel;
import timer.WinOrLoseTimer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * LogoutButton is a customized JButton used to log the player out of the game.
 * It stops the game timer, shuts down the game state, and navigates back to the main menu.
 */
public class LogoutButton extends JButton {

    /**
     * Constructs the LogoutButton with a custom icon and sets its behavior.
     * Clicking the button will log the user out if the game has not already ended.
     */
    public LogoutButton() {
        ImageIcon icon = new ImageIcon(Resources.Instance.logoutButton);
        setIcon(icon);

        setBounds(10, 10, icon.getIconWidth(), icon.getIconHeight());

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prevent logout if the game has already ended
                if (!Safari.Instance.getWinOrLose().equals("")) return;

                // Stop the game timer
                WinOrLoseTimer.getInstance().stopTimer();

                // Shut down game state
                Safari.Instance.shutDown();

                // Navigate back to the main menu
                CardPanel.Instance.showCard("menu");
            }
        });

        // Make button visually clean (icon-only, no border/focus)
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }
}
