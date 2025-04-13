package panels.feedback;

import core.Resources;
import panels.game.toolbar.ToolBarCardLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * {@code BasicFeedBackPanel} is an abstract base panel used to display
 * simple feedback messages with an associated image.
 * It uses a {@code GridBagLayout} to vertically align an image and message label.
 */
public abstract class BasicFeedBackPanel extends JPanel {

    protected GridBagConstraints gbc = new GridBagConstraints();

    /**
     * Constructs a new {@code BasicFeedBackPanel} with a message and an image.
     * It hides the toolbar by switching the toolbar card to "void",
     * and arranges the components using {@code GridBagLayout}.
     *
     * @param message the message text to be displayed
     * @param image   the image to be shown above the message
     */
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

    /**
     * Updates the position of this panel to be centered within its parent container.
     * This should be called after the panel is added to a visible parent.
     */
    public void updatePosition() {
        setBounds(getParent().getWidth()/2 - getWidth() / 2, getParent().getHeight()/2 - getHeight() / 2, getWidth(), getHeight());
    }
}
