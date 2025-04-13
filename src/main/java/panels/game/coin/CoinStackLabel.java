package panels.game.coin;

import core.Resources;

import javax.swing.*;

/**
 * The CoinStackLabel class is a JLabel that displays an image of a coin stack.
 * The image is loaded from the resources and set as the icon of the label.
 */
public class CoinStackLabel extends JLabel {

    /**
     * The constructor for CoinStackLabel, which loads the coin stack image from resources
     * and sets it as the icon of the label.
     */
    public CoinStackLabel() {
        ImageIcon image = new ImageIcon(Resources.Instance.coinStack);
        setIcon(image);

        setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
    }
}
