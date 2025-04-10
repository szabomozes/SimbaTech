package panels.game.toolbar;

import core.Resources;
import panels.game.toolbar.buttons.BuyingSellingToolBarBin;
import safari.Safari;

import javax.swing.*;
import java.awt.*;

public class SellingToolBar extends JPanel {
    private JLabel text;

    public SellingToolBar() {
        setLayout(new GridBagLayout());
        setBackground(new Color(40, 40, 40));
        setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));

        text = new OutlineLabel("Eladás mód");
        text.setFont(Resources.Instance.menu_font.deriveFont(35f));
        text.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 0, 0);

        add(text, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);

        add(new BuyingSellingToolBarBin(), gbc);
    }
}
