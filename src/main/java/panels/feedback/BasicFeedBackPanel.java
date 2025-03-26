package panels.feedback;


import panels.game.toolbar.ToolBarCardLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class BasicFeedBackPanel extends JPanel {

    public BasicFeedBackPanel(String message, BufferedImage image, JButton button) {
        button.setFocusPainted(false);
        ToolBarCardLayout.Instance.showCard("void");

        setSize(new Dimension(300, 200));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel imageLabel = new JLabel(new ImageIcon(image));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(imageLabel, gbc);

        JLabel messageLabel = new JLabel(message);
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 20, 10, 20);
        add(messageLabel, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(button, gbc);
    }

    public void updatePosition() {
        setBounds(getParent().getWidth()/2 - getWidth() / 2, getParent().getHeight()/2 - getHeight() / 2, getWidth(), getHeight());
    }
}
