package panels.game.toolbar;

import panels.game.toolbar.buttons.BuyingSellingToolBarBin;

import javax.swing.*;
import java.awt.*;

public class BuyingToolBar extends JPanel {
    public BuyingToolBar() {
        setLayout(new GridBagLayout());


        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        add(new BuyingSellingToolBarBin(), gbc);

        setBackground(new Color(40, 40, 40));
        setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
    }
}
