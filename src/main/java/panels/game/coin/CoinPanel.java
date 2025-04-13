package panels.game.coin;

import javax.swing.*;
import java.awt.*;

/**
 * The CoinPanel class is a JPanel that displays both an image of a coin stack and the number of coins.
 * The image and the number are placed on the panel, and the layout is dynamically updated by calling the `updateLayout` method.
 */
public class CoinPanel extends JPanel {

    /**
     * The CoinStackLabel that displays the image of the coin stack.
     */
    private final CoinStackLabel image = new CoinStackLabel();

    /**
     * The CoinNumberLabel that displays the number of coins next to the image.
     */
    private final CoinNumberLabel number = new CoinNumberLabel(image);

    /**
     * The constructor for CoinPanel, which sets the layout and adds the necessary components.
     */
    public CoinPanel() {
        setLayout(null);
        setOpaque(false);

        add(image);
        add(number);

        updateLayout();
    }

    /**
     * Updates the layout so that the image and number are positioned correctly within the panel.
     * The positioning is based on the width and height of the image and the number.
     */
    public void updateLayout() {
        number.updateText();

        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        FontMetrics fm = number.getFontMetrics(number.getFont());
        int textWidth = fm.stringWidth(number.getText());
        int textHeight = fm.getHeight();

        image.setBounds(0, 0, imageWidth, imageHeight);
        number.setBounds(imageWidth + 10, (imageHeight - textHeight) / 2, textWidth + 20, textHeight);

        setBounds(70, 10, imageWidth + 10 + textWidth + 20, imageHeight);
        revalidate();
        repaint();
    }

    /**
     * Custom rendering method that is responsible for drawing the component.
     * The background is drawn as a rounded rectangle.
     *
     * @param g The Graphics object used for rendering the component.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        int cornerRadius = 20;
        g2d.setColor(new Color(255, 255, 255, 0));
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2d.setColor(new Color(255, 255, 255));
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        number.updateText();
    }
}
