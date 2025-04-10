package panels.game.toolbar;

import core.Resources;

import javax.swing.*;
import java.awt.*;

public class ToolBarFeedBackPanel extends JPanel {
    private JLabel text;

    public ToolBarFeedBackPanel() {
        setBackground(new Color(40, 40, 40));
        setLayout(new GridBagLayout());

       // text = new OutlineLabel("Mozogj a vadőrrel vagy kattints rá a kilépéshez!");
        text = new OutlineLabel("");
        text.setFont(Resources.Instance.menu_font.deriveFont(35f));
        text.setForeground(Color.WHITE);

        add(text, new GridBagConstraints());
    }
}
