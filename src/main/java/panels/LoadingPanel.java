package panels;

import core.Resources;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * {@code LoadingPanel} is a custom JPanel that displays an animated background
 * and a loading message with an hourglass image while the map is being generated.
 * The background color cycles smoothly between RGB values.
 */
public class LoadingPanel extends JPanel {
    private int r = 0, g = 0, b = 0;
    private boolean increasing = true;
    private Image hourglass;
    private String text = "Pálya generálása...";
    private Random rnd = new Random();

    /**
     * Constructs the {@code LoadingPanel} and starts the background animation.
     */
    public LoadingPanel() {
        setPreferredSize(new Dimension(400, 300));
        hourglass = Resources.Instance.hourglass;
        setBackground(Color.BLACK);

        // Gradient background animation
        Timer timer = new Timer(1, e -> {
            if (increasing) {
                r = Math.min(255, r + rnd.nextInt(1, 5));
                g = Math.min(255, g + rnd.nextInt(1, 5));
                b = Math.min(255, b + rnd.nextInt(1, 5));
                if (r == 255 || g == 255 || b == 255) {
                    increasing = false;
                }
            } else {
                r = Math.max(0, r - rnd.nextInt(1, 5));
                g = Math.max(0, g - rnd.nextInt(1, 5));
                b = Math.max(0, b - rnd.nextInt(1, 5));
                if (r == 0 || g == 0 || b == 0) {
                    increasing = true;
                }
            }
            setBackground(new Color(r, g, b));
            repaint();
        });

        timer.start();
    }

    /**
     * Custom painting of the panel, including the hourglass image and
     * center-aligned loading text with a black contour and white fill.
     *
     * @param g the Graphics context in which to paint
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the hourglass image
        if (hourglass != null) {
            int x = (getWidth() - hourglass.getWidth(null)) / 2;
            int y = (getHeight() - hourglass.getHeight(null)) / 2 + 50;
            g.drawImage(hourglass, x, y, null);
        }

        // Draw loading text with black outline and white fill
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setFont(Resources.Instance.menu_font.deriveFont(40f));
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textX = (getWidth() - textWidth) / 2;
        int textY = getHeight() / 3;

        // Black outline
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(10));
        g2.drawString(text, textX - 1, textY - 1);
        g2.drawString(text, textX - 1, textY + 1);
        g2.drawString(text, textX + 1, textY - 1);
        g2.drawString(text, textX + 1, textY + 1);

        // White fill
        g2.setColor(Color.WHITE);
        g2.drawString(text, textX, textY);
    }
}
