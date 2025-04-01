package panels.feedback;


import core.Resources;
import panels.game.toolbar.ToolBarCardLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class BasicFeedBackPanel extends JPanel {

    protected GridBagConstraints gbc = new GridBagConstraints();

    public BasicFeedBackPanel(String message, BufferedImage image) {
        ToolBarCardLayout.Instance.showCard("void");

        setSize(new Dimension(300, 200));
        setLayout(new GridBagLayout());

        JLabel imageLabel = new JLabel(new ImageIcon(image));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(imageLabel, gbc);

        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(Resources.Instance.menu_font.deriveFont(20f));
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 20, 10, 20);
        add(messageLabel, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
    }

    public void updatePosition() {
        setBounds(getParent().getWidth()/2 - getWidth() / 2, getParent().getHeight()/2 - getHeight() / 2, getWidth(), getHeight());
    }
}
