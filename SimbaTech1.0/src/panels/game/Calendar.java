package panels.game;

import core.Resources;
import panels.CardPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calendar extends JButton {
    private int date = 1;

    public static Calendar Instance = new Calendar();

    private Calendar() {
        ImageIcon icon = new ImageIcon(Resources.Instance.calender);
        setIcon(icon);

        int width = icon.getIconWidth();
        int height = icon.getIconHeight();
        setBounds(CardPanel.Instance.getWidth() - width - 10, 10, width, height);

        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);

        updateText();

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                date++;
                updateText();
                System.out.println("A dátum: " + date);
            }
        });
    }

    private void updateText() {
        setText(String.valueOf(date));
        setHorizontalTextPosition(JButton.CENTER);
        setVerticalTextPosition(JButton.CENTER);
        setFont(Resources.Instance.menu_font.deriveFont(35f));
        setForeground(Color.BLACK);
    }

    public void updatePosition() {
        setBounds(CardPanel.Instance.getWidth() - getWidth() - 10, 10, getWidth(), getHeight());
    }

}
