package panels.game.toolbar.buttons.speed;

import core.Resources;
import panels.CardPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class SpeedButton extends JButton {

    private SpeedEnum speedEnum = SpeedEnum.SNAIL;

    public static SpeedButton Instance = new SpeedButton();

    private final int x = 30;
    private final int y = 45;

    private SpeedButton() {

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
                speedEnum = speedEnum.next();
                ImageIcon icon = new ImageIcon(getBufferedImageBySpeedEnum());
                setIcon(icon);
            }
        });
    }

    private BufferedImage getBufferedImageBySpeedEnum() {
        switch (speedEnum) {
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
        return Resources.Instance.speedSnail;
    }

    public void updatePosition() {
        setBounds(CardPanel.Instance.getWidth() - getWidth() - x, y, getWidth(), getHeight());
    }

    public SpeedEnum getSpeedEnum() {
        return speedEnum;
    }

    public void resetSpeed() {
        while (speedEnum != SpeedEnum.SNAIL) {
            speedEnum = speedEnum.next();
        }
        ImageIcon icon = new ImageIcon(getBufferedImageBySpeedEnum());
        setIcon(icon);
    }

}
