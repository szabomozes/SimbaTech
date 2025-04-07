package panels.feedback;

import core.Resources;
import panels.CardPanel;
import panels.game.EventPanel;
import panels.game.toolbar.ToolBarCardLayout;
import safari.Safari;

import javax.swing.*;

public class MessageFeedBackPanel extends BasicFeedBackPanel{
    public MessageFeedBackPanel(String text, String toolBarCardLayout) {
        super(text, Resources.Instance.badFeddBack);

        JButton button = new JButton("Rendben");
        button.setFocusPainted(false);
        button.setFont(Resources.Instance.menu_font.deriveFont(20f));
        button.addActionListener(e-> {
            ToolBarCardLayout.Instance.showCard(toolBarCardLayout);
            ((EventPanel) getParent()).setFeedback(null);
        });

        add(button, gbc);
    }
}