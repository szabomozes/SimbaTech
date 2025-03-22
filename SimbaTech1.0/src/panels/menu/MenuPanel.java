package panels.menu;

import core.Resources;
import panels.menu.buttons.ButtonsPanel;
import panels.menu.text.*;
import panels.menu.icon.LogoLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuPanel extends JPanel {
    private final BufferedImage backgroundImage = Resources.Instance.menu_background;
    private int xOffset = 0;
    private int direction = -1;

    public MenuPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 0, 10);

        JLabel logoLabel = new LogoLabel();
        add(logoLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 10, 10, 10);
        JLabel title = new TitleLabel();
        add(title, gbc);

        gbc.gridy++;
        JLabel subtitle = new SubtitleLabel();
        add(subtitle, gbc);

        gbc.gridy++;
        JPanel buttonPanel = new ButtonsPanel();
        add(buttonPanel, gbc);

        new Timer(0, e -> moveBackground()).start();
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            int frameHeight = getHeight();
            int newWidth = (int) ((double) backgroundImage.getWidth() / backgroundImage.getHeight() * frameHeight);
            g.drawImage(backgroundImage, xOffset, 0, newWidth, frameHeight, this);
        }
    }
}
