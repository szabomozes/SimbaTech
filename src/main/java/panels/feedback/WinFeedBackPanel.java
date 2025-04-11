package panels.feedback;

import core.Resources;
import safari.Safari;
import panels.CardPanel;
import timer.WinOrLoseTimer;

import javax.swing.*;

public class WinFeedBackPanel extends BasicFeedBackPanel{
    public WinFeedBackPanel() {
        super("Gratulálok! Csodálatos munkát végeztél!", Resources.Instance.winFeddBack);
        add(getButton(), gbc);
    }
    private JButton getButton() {
        WinOrLoseTimer.getInstance().stopTimer();
        Safari.Instance.shutDown();
        JButton button = new JButton("Kilépés");
        button.setFocusPainted(false);
        button.setFont(Resources.Instance.menu_font.deriveFont(20f));
        button.addActionListener(e-> {
            Safari.Instance.shutDown();
            CardPanel.Instance.showCard("menu");
        });
        return button;
    }
}
