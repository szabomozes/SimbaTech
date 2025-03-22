package panels.game.coin;

import core.Resources;
import javax.swing.*;

public class CoinStackLabel extends JLabel {
    public CoinStackLabel() {
        ImageIcon image = new ImageIcon(Resources.Instance.coinStack);
        setIcon(image);

        setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
    }
}
