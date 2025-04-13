package panels.game.toolbar.buttons.speed;

import core.Resources;
import safari.Speed;
import safari.SpeedEnum;
import panels.CardPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * A toolbar button that cycles through different simulation speeds when clicked.
 * The button icon changes to represent the current speed using animal-themed icons.
 */
public class SpeedButton extends JButton {

    private final int x = 30;
    private final int y = 45;

    /**
     * Constructs a SpeedButton, sets its icon based on the current speed,
     * and adds a click listener to cycle through the speed options.
     */
    public SpeedButton() {
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);

        ImageIcon icon = new ImageIcon(getBufferedImageBySpeedEnum());
        setIcon(icon);

        int width = icon.getIconWidth();
        int height = icon.getIconHeight();
        setBounds(CardPanel.Instance.getWidth() - width - x, y, width, height);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Speed.Instance.speedEnum = Speed.Instance.speedEnum.next();
                ImageIcon icon = new ImageIcon(getBufferedImageBySpeedEnum());
                setIcon(icon);
            }
        });
    }

    /**
     * Returns the appropriate icon image based on the current speed enum.
     *
     * @return A BufferedImage representing the current speed level.
     */
    private BufferedImage getBufferedImageBySpeedEnum() {
        switch (Speed.Instance.speedEnum) {
            case SNAIL -> {
                return Resources.Instance.speedSnail;
            }
            case HIPPOPOTAMUS -> {
                return Resources.Instance.speedHippopotamus;
            }
            case EAGLE -> {
                return Resources.Instance.speedEadle;
            }
        }
        return Resources.Instance.speedSnail; // Fallback
    }

    /**
     * Updates the button's position based on the width of the CardPanel.
     */
    public void updatePosition() {
        setBounds(CardPanel.Instance.getWidth() - getWidth() - x, y, getWidth(), getHeight());
    }

    /**
     * Resets the speed to the slowest setting (SNAIL) and updates the icon accordingly.
     */
    public void resetSpeed() {
        while (Speed.Instance.speedEnum != SpeedEnum.SNAIL) {
            Speed.Instance.speedEnum = Speed.Instance.speedEnum.next();
        }
        ImageIcon icon = new ImageIcon(getBufferedImageBySpeedEnum());
        setIcon(icon);
    }
}
