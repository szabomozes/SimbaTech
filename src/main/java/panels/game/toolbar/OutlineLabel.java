package panels.game.toolbar;

import javax.swing.*;
import java.awt.*;

public class OutlineLabel extends JLabel {

    public OutlineLabel(String text) {
        super(text);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setFont(getFont());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;

        // Fekete körvonal (stroke)
        g2.setColor(Color.BLACK);
        int thickness = 2; // körvonal vastagság
        for (int dx = -thickness; dx <= thickness; dx++) {
            for (int dy = -thickness; dy <= thickness; dy++) {
                if (dx != 0 || dy != 0)
                    g2.drawString(getText(), x + dx, y + dy);
            }
        }


        // Fehér töltés
        g2.setColor(getForeground());
        g2.drawString(getText(), x, y);

        g2.dispose();
    }
}
