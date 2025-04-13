package panels.game.coin;

import core.Resources;
import safari.Safari;

import javax.swing.*;
import java.awt.*;

/**
 * CoinNumberLabel is a JLabel that displays the current coin count in the game.
 * It updates dynamically based on the coin value from the Safari instance.
 */
public class CoinNumberLabel extends JLabel {
    private int number;
    private int height;

    /**
     * Constructs the CoinNumberLabel with a reference height from the associated CoinStackLabel.
     *
     * @param image The CoinStackLabel used to determine the height for this label.
     */
    public CoinNumberLabel(CoinStackLabel image) {
        height = image.getHeight();
        number = Safari.Instance.coin;
        setFont(Resources.Instance.menu_font.deriveFont(50f));
        updateText();
    }

    /**
     * Updates the label text to reflect the current coin amount in the Safari instance.
     * Also recalculates the label's bounds to fit the new text width.
     */
    public void updateText() {
        number = Safari.Instance.coin;
        String text = number + " $";
        setText(text);

        FontMetrics fm = getFontMetrics(getFont());
        int textWidth = fm.stringWidth(text);

        // Position the label to the right of the coin icon with the same height
        setBounds(60, 0, textWidth, height);
    }
}
