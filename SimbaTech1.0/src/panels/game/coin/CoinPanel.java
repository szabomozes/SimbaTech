package panels.game.coin;

import javax.swing.*;
import java.awt.*;

public class CoinPanel extends JPanel {
    private final CoinStackLabel image = new CoinStackLabel();
    private final CoinNumberLabel number = new CoinNumberLabel(image);

    public CoinPanel() {
        setLayout(null);
        setOpaque(false);

        add(image);
        add(number);

        updateLayout();
    }

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
