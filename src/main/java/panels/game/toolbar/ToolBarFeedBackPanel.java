package panels.game.toolbar;

import core.Resources;

import javax.swing.*;
import java.awt.*;

/**
 * {@code ToolBarFeedBackPanel} is a feedback panel used in the toolbar area to display messages or instructions.
 * It uses a centered label to present the feedback in a visually consistent style.
 */
public class ToolBarFeedBackPanel extends JPanel {
    private JLabel text;

    /**
     * Constructs a {@code ToolBarFeedBackPanel} with predefined styling and an empty label.
     * The font and color are initialized based on the application's shared resources.
     */
    public ToolBarFeedBackPanel() {
        setBackground(new Color(40, 40, 40));
        setLayout(new GridBagLayout());

        // text = new OutlineLabel("Move with the ranger or click to exit!");
        text = new OutlineLabel("");
        text.setFont(Resources.Instance.menu_font.deriveFont(35f));
        text.setForeground(Color.WHITE);

        add(text, new GridBagConstraints());
    }
}
