package panels.game.toolbar;

import javax.swing.*;
import java.awt.*;

/**
 * The OutlineLabel class is a custom JLabel that renders text with an outlined effect.
 * It draws the text with a black outline and fills the text with the label's foreground color.
 */
public class OutlineLabel extends JLabel {

    /**
     * Constructor for the OutlineLabel class. It initializes the label with the provided text.
     * The label is set to be non-opaque to allow custom rendering.
     *
     * @param text The text to be displayed by the label.
     */
    public OutlineLabel(String text) {
        super(text);
        setOpaque(false);
    }

    /**
     * Paints the component, rendering the text with an outline effect.
     * The outline is drawn in black, and the text is filled with the label's foreground color.
     *
     * @param g The Graphics object used for rendering the text.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Set the font and rendering hints
        g2.setFont(getFont());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;

        // Draw the black outline (stroke) for the text
        g2.setColor(Color.BLACK);
        int thickness = 2; // Outline thickness
        for (int dx = -thickness; dx <= thickness; dx++) {
            for (int dy = -thickness; dy <= thickness; dy++) {
                if (dx != 0 || dy != 0)
                    g2.drawString(getText(), x + dx, y + dy);
            }
        }

        // Fill the text with the foreground color of the label
        g2.setColor(getForeground());
        g2.drawString(getText(), x, y);

        g2.dispose();
    }
}
