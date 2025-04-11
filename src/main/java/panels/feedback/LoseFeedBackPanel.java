package panels.feedback;

import core.Resources;
import panels.CardPanel;
import safari.Safari;
import timer.WinOrLoseTimer;

import javax.swing.*;

public class LoseFeedBackPanel extends BasicFeedBackPanel{
    public LoseFeedBackPanel() {
        super("Vesztettél!", Resources.Instance.loseFeddBack);
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
