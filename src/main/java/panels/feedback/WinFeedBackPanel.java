package panels.feedback;

import core.Resources;
import logic.Logic;
import panels.CardPanel;

import javax.swing.*;

public class WinFeedBackPanel extends BasicFeedBackPanel{
    public WinFeedBackPanel() {
        super("Gratulálok! Csodálatos munkát végeztél!", Resources.Instance.winFeddBack, getButton());
    }
    private static JButton getButton() {
        Logic.Instance.shutDown();
        JButton button = new JButton("Kilépés");
        button.addActionListener(e-> {
            Logic.Instance.shutDown();
            CardPanel.Instance.showCard("menu");
        });
        return button;
    }
}
