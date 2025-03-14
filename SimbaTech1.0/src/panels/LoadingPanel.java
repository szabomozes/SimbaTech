package panels;

import core.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.TimerTask;

public class LoadingPanel extends JPanel {
        private final BufferedImage backgroundImage = Resources.Instance.menu_background;
        private int xOffset = 0;
        private int direction = -1;
        private int messageIndex = 0;

        private final BufferedImage image = Resources.Instance.menu_logo;
        private double angle = 0;
        private String currentMessage;
        private String[] messages = {
                "Tipp: Szöveg1!",
                "Tipp: Szöveg2!",
                "Tipp: Szöveg3!",
                "Tipp: Szöveg4!",
                "Tipp: Szöveg5!"
        };
        private Random random = new Random();

        public LoadingPanel() {

            java.util.Timer backgroundTimer = new java.util.Timer(true);
            backgroundTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    moveBackground();
                }
            }, 1, 20);

            javax.swing.Timer rotationTimer = new javax.swing.Timer(10, e -> {
                angle -= Math.toRadians(1);
                if (angle <= -2 * Math.PI) angle = 0;
                repaint();
            });

            javax.swing.Timer textChangeTimer = new javax.swing.Timer(3000, e -> {
                int newMessageIndex = random.nextInt(messages.length);
                while (newMessageIndex == messageIndex) {
                    newMessageIndex = random.nextInt(messages.length);
                }
                messageIndex = newMessageIndex;
                currentMessage = messages[messageIndex];
                repaint();
            });
            textChangeTimer.setRepeats(true);
            textChangeTimer.start();

            currentMessage = messages[random.nextInt(messages.length)];
            rotationTimer.start();
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

            int scrollSpeed = 1;
            xOffset += direction * scrollSpeed;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            int w = getWidth();
            int h = getHeight();

            if (backgroundImage != null) {
                int newWidth = (int) ((double) backgroundImage.getWidth() / backgroundImage.getHeight() * h);
                g.drawImage(backgroundImage, xOffset, 0, newWidth, h, this);
            }

            int imgW = image.getWidth(null);
            int imgH = image.getHeight(null);

            int x = (w - imgW) / 2;
            int y = (h - imgH) / 2;

            AffineTransform transform = new AffineTransform();
            transform.translate(x + imgW / 2.0, y + imgH / 2.0);
            transform.rotate(angle);
            transform.translate(-imgW / 2.0, -imgH / 2.0);
            g2d.setTransform(transform);
            g2d.drawImage(image, 0, 0, null);

            g2d.setTransform(new AffineTransform());

            g2d.setFont(Resources.Instance.menu_font.deriveFont(40f));
            g2d.setColor(Color.BLACK);
            FontMetrics fm = g2d.getFontMetrics();
            int msgWidth = fm.stringWidth(currentMessage);
            int msgX = (w - msgWidth) / 2;
            int msgY = h - 40;

            g2d.drawString(currentMessage, msgX, msgY);
        }

    }