package panels.feedback;

import core.Resources;
import panels.CardPanel;
import panels.game.EventPanel;
import panels.game.toolbar.ToolBarCardLayout;
import safari.Safari;

import javax.swing.*;

public class MessageFeedBackPanel extends BasicFeedBackPanel{
    public MessageFeedBackPanel(String text) {
        super(text, Resources.Instance.badFeddBack);
        add(getButton(), gbc);
    }
    private JButton getButton() {
        Safari.Instance.shutDown();
        JButton button = new JButton("Rendben");
        button.setFocusPainted(false);
        button.setFont(Resources.Instance.menu_font.deriveFont(20f));
        button.addActionListener(e-> {
            ToolBarCardLayout.Instance.showCard("toolbar");
            ((EventPanel) getParent()).setFeedback(null);
        });
        return button;
    }
}