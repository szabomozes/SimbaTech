package panels.feedback;

import core.Resources;
import safari.Safari;
import panels.CardPanel;

import javax.swing.*;

public class WinFeedBackPanel extends BasicFeedBackPanel{
    public WinFeedBackPanel() {
        super("Gratulálok! Csodálatos munkát végeztél!", Resources.Instance.winFeddBack, getButton());
    }
    private static JButton getButton() {
        Safari.Instance.shutDown();
        JButton button = new JButton("Kilépés");
        button.addActionListener(e-> {
            Safari.Instance.shutDown();
            CardPanel.Instance.showCard("menu");
        });
        return button;
    }
}
