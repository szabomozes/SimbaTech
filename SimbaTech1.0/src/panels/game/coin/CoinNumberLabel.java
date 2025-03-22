package panels.game.coin;

import core.Resources;
import logic.Logic;

import javax.swing.*;
import java.awt.*;

public class CoinNumberLabel extends JLabel {
    private int number;
    private int height;

    public CoinNumberLabel(CoinStackLabel image) {
        height = image.getHeight();
        number = Logic.Instance.getCoin();
        setFont(Resources.Instance.menu_font.deriveFont(50f));
        updateText();
    }

    public void updateText() {
        number = Logic.Instance.getCoin();
        String text = number + " $";
        setText(text);

        FontMetrics fm = getFontMetrics(getFont());
        int textWidth = fm.stringWidth(text);

        setBounds(60, 0, textWidth, height);
    }
}
