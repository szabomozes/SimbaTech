package panels;

import core.Resources;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Random;

public class LoadingPanel extends JPanel {
    private final BufferedImage backgroundImage = Resources.Instance.menu_background;
    private int xOffset = 0;
    private int direction = -1;
    private int messageIndex = 0;

    private final BufferedImage image = Resources.Instance.menu_logo;
    private double angle = 0;
    private String currentMessage;
    private final String[] messages = {
            "Tipp: Szöveg1!",
            "Tipp: Szöveg2!",
            "Tipp: Szöveg3!",
            "Tipp: Szöveg4!",
            "Tipp: Szöveg5!"
    };
    private final Random random = new Random();

    public LoadingPanel() {
        new Timer(20, e -> moveBackground()).start();

        new Timer(10, e -> {
            angle -= Math.toRadians(1);
            if (angle <= -2 * Math.PI) angle = 0;
            repaint();
        }).start();

        new Timer(3000, e -> updateMessage()).start();

        currentMessage = messages[random.nextInt(messages.length)];
    }

    private void moveBackground() {
        if (backgroundImage == null) return;

        int frameWidth = getWidth();
        int frameHeight = getHeight();
        int newWidth = (int) ((double) backgroundImage.getWidth() / backgroundImage.getHeight() * frameHeight);

        if (xOffset <= -(newWidth - frameWidth)) {
            direction = 1;
        } else if (xOffset >= 0) {
            direction = -1;
        }

        xOffset += direction;
        repaint();
    }

    private void updateMessage() {
        int newMessageIndex;
        do {
            newMessageIndex = random.nextInt(messages.length);
        } while (newMessageIndex == messageIndex);

        messageIndex = newMessageIndex;
        currentMessage = messages[messageIndex];
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        if (backgroundImage != null) {
            int newWidth = (int) ((double) backgroundImage.getWidth() / backgroundImage.getHeight() * h);
            g.drawImage(backgroundImage, xOffset, 0, newWidth, h, this);
        }

        drawRotatedImage(g2d);

        g2d.setFont(Resources.Instance.menu_font.deriveFont(40f));
        g2d.setColor(Color.BLACK);
        FontMetrics fm = g2d.getFontMetrics();
        int msgWidth = fm.stringWidth(currentMessage);
        int msgX = (w - msgWidth) / 2;
        int msgY = h - 40;

        g2d.drawString(currentMessage, msgX, msgY);
    }

    private void drawRotatedImage(Graphics2D g2d) {
        int w = getWidth();
        int h = getHeight();

        int imgW = image.getWidth();
        int imgH = image.getHeight();

        int centerX = w / 2;
        int centerY = h / 2;

        AffineTransform tx = new AffineTransform();
        tx.translate(centerX, centerY);
        tx.rotate(angle);
        tx.translate(-imgW / 2.0, -imgH / 2.0);

        AffineTransform old = g2d.getTransform();
        g2d.setTransform(tx);
        g2d.drawImage(image, 0, 0, null);
        g2d.setTransform(old);
    }

}
