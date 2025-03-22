package panels.game;

import core.Resources;
import logic.Logic;
import panels.CardPanel;

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
                Logic.Instance.shutDown();
                CardPanel.Instance.showCard("menu");
            }
        });

        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }
}