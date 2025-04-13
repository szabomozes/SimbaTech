package panels.game.toolbar;

import core.Resources;
import panels.game.toolbar.buttons.BuyingSellingToolBarBin;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code SellingToolBar} class represents the toolbar used in the game for selling items.
 * It displays a title label ("Eladás mód") and includes a {@code BuyingSellingToolBarBin} component.
 */
public class SellingToolBar extends JPanel {
    private JLabel text;

    /**
     * Constructs a {@code SellingToolBar} with a layout and appearance customized
     * for the selling mode. It includes a label and a trash bin component,
     * laid out using {@code GridBagLayout}.
     */
    public SellingToolBar() {
        setLayout(new GridBagLayout());
        setBackground(new Color(40, 40, 40));
        setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));

        text = new OutlineLabel("Eladás mód");
        text.setFont(Resources.Instance.menu_font.deriveFont(35f));
        text.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();

        // Add the title label to the top-left
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 0, 0);

        add(text, gbc);

        // Add the bin component centered
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);

        add(new BuyingSellingToolBarBin(), gbc);
    }
}
