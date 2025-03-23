package panels.game.toolbar;

import panels.game.toolbar.buttons.BuyingToolBarBin;

import javax.swing.*;
import java.awt.*;

public class BuyingToolBar extends JPanel {
    public BuyingToolBar() {
        setLayout(new GridBagLayout());

        setBackground(Color.DARK_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        add(new BuyingToolBarBin(), gbc);
    }
}
