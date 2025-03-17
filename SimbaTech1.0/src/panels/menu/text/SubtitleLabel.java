package panels.menu.text;


import core.Resources;

import javax.swing.*;
import java.awt.*;

public class SubtitleLabel extends JLabel{
    public SubtitleLabel() {
        super("A vadon a te játszótered - építsd, védd meg, urald!");
        setFont(Resources.Instance.menu_font.deriveFont(30f));
        setForeground(Color.BLACK);
    }

    // Fehér keret a szöveg köré
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        String text = getText();
        FontMetrics fm = g2.getFontMetrics(getFont());
        int x = 0; // Vízszintes kezdőpozíció
        int y = fm.getAscent(); // Függőleges igazítás

        // Kontúr rajzolása (fekete körvonal)
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(10)); // Vastagabb vonal
        g2.drawString(text, x - 1, y - 1);
        g2.drawString(text, x - 1, y + 1);
        g2.drawString(text, x + 1, y - 1);
        g2.drawString(text, x + 1, y + 1);

        super.paintComponent(g);
    }
}
