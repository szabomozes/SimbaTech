package panels.feedback;

import core.Resources;
import panels.game.EventPanel;
import panels.game.toolbar.ToolBarCardLayout;
import panels.game.GameStateTriggerButton;


import javax.swing.*;
import java.awt.*;

public class GameStatePanel extends BasicFeedBackPanel {
    public GameStatePanel(String text, String toolBarCardLayout) {
        super(text, Resources.Instance.info);
        setSize(new Dimension(300, 300));

        JButton button = new JButton("OK");
        button.setFocusPainted(false);
        button.addActionListener(e -> {
            ToolBarCardLayout.Instance.showCard(toolBarCardLayout);
            ((EventPanel) getParent()).setFeedback(null);
            GameStateTriggerButton.Instance.setClicked(true);
        });
        add(button, gbc);
    }
}