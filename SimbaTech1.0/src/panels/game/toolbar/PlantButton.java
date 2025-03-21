package panels.game.toolbar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class PlantButton extends JButton {
    protected String message;
    public PlantButton(BufferedImage image, int x, int y) {
        ImageIcon icon = new ImageIcon(image);
        setIcon(icon);
        int width = icon.getIconWidth();
        int height = icon.getIconHeight();
        setBounds(x, y, width, height);

        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // image
                System.out.println(message);
            }
        });
    }
}
