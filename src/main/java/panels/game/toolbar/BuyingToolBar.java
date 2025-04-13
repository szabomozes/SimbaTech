package panels.game.toolbar;

import panels.game.toolbar.buttons.BuyingSellingToolBarBin;

import javax.swing.*;
import java.awt.*;

/**
 * The BuyingToolBar class represents a toolbar used for buying items in the game.
 * It arranges the toolbar components using a GridBagLayout and adds a custom button component.
 */
public class BuyingToolBar extends JPanel {

    /**
     * Constructor for the BuyingToolBar class. It sets the layout, adds the toolbar button,
     * and configures the appearance of the toolbar.
     */
    public BuyingToolBar() {
        setLayout(new GridBagLayout());

        // GridBagConstraints to manage the layout of components
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Add the BuyingSellingToolBarBin button to the toolbar
        add(new BuyingSellingToolBarBin(), gbc);

        // Set the background color and border for the toolbar
        setBackground(new Color(40, 40, 40));
        setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
    }
}
