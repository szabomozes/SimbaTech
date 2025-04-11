package panels.game;

import core.Resources;
import safari.Safari;
import panels.CardPanel;
import timer.WinOrLoseTimer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutButton extends JButton {

    public LogoutButton() {
        ImageIcon icon = new ImageIcon(Resources.Instance.logoutButton);
        setIcon(icon);

        setBounds(10, 10, icon.getIconWidth(), icon.getIconHeight());

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Safari.Instance.getWinOrLose().equals("")) return;
                WinOrLoseTimer.getInstance().stopTimer();
                Safari.Instance.shutDown();
                CardPanel.Instance.showCard("menu");
            }
        });

        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }
}