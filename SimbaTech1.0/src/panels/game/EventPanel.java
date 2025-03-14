package panels.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import core.Resources;
import panels.CardPanel;

public class EventPanel extends JPanel {
    private final BufferedImage background = Resources.Instance.map;
    private int offsetX = 0, offsetY = 0;
    private int lastX, lastY;
    private boolean dragging = false;
    private final LogoutButton logoutButton = new LogoutButton();

    /*
    private List<MovingImage> movingImages;
    private MovingImage selectedImage = null;
    */

    public EventPanel() {


        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastX = e.getX();
                lastY = e.getY();

                /*
                if (newImagePath1 != null) {
                    MovingImage tempImage = new MovingImage(newImagePath1, newImagePath2, e.getX() - offsetX, e.getY() - offsetY);
                    movingImages.add(tempImage);
                    newImagePath1 = null;
                    repaint();
                } else {
                    for (MovingImage img : movingImages) {
                        if (img.contains(e.getX() - offsetX, e.getY() - offsetY)) {
                            selectedImage = img;
                            return;
                        }
                    }
                    dragging = true;
                }
                */
                if (logoutButton.contains(e.getX(), e.getY())) {
                    CardPanel.Instance.showCard("menu");
                }
                dragging = true;

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                dragging = false;
                /*
                if (selectedImage != null) {
                    selectedImage.startMovingTo(e.getX() - offsetX, e.getY() - offsetY, GamePanel.this);
                    selectedImage = null;
                }
                */

            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragging) {
                    int dx = e.getX() - lastX;
                    int dy = e.getY() - lastY;

                    offsetX += dx;
                    offsetY += dy;

                    offsetX = Math.max(Math.min(0, offsetX), getWidth() - background.getWidth());
                    offsetY = Math.max(Math.min(0, offsetY), getHeight() - background.getHeight());

                    lastX = e.getX();
                    lastY = e.getY();

                    repaint();
                }
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, offsetX, offsetY, this);
        }
        /*
        for (MovingImage img : movingImages) {
            img.draw(g, offsetX, offsetY);
        }
        */
        logoutButton.draw(g);
    }
}
