package panels.game;

import core.Resources;
import panels.CardPanel;

import javax.swing.*;
import java.awt.*;

public class Calendar extends JLabel {
    private int date = 1;

    public static Calendar Instance = new Calendar();

    private Calendar() {
        ImageIcon icon = new ImageIcon(Resources.Instance.calender);
        setIcon(icon);

        int width = icon.getIconWidth();
        int height = icon.getIconHeight();
        setBounds(CardPanel.Instance.getWidth() - width - 10, 10, width, height);

        setOpaque(false);

        setHorizontalTextPosition(JButton.CENTER);
        setVerticalTextPosition(JButton.CENTER);
        setFont(Resources.Instance.menu_font.deriveFont(35f));
        setForeground(Color.BLACK);

        updateText();
    }

    private void updateText() {
        setText(String.valueOf(date));
        repaint();
    }

    public void updatePosition() {
        setBounds(CardPanel.Instance.getWidth() - getWidth() - 10, 10, getWidth(), getHeight());
    }

    public void setDate(int date) {
        this.date = date;
        updateText();
    }

}
