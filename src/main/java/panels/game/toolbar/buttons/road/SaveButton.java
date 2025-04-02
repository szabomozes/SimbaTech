package panels.game.toolbar.buttons.road;

import core.Resources;
import panels.game.toolbar.ToolBarCardLayout;
import panels.game.toolbar.buttons.shop.BasicLeftToolBarButton;
import safari.Safari;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class SaveButton extends JButton {
    private BufferedImage image = Resources.Instance.saveButton;
    private final int x = 500;
    private final int y = 45;

    public SaveButton() {
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
                System.out.println("back to toolbar");
                CardLayout cardLayout = (CardLayout) ToolBarCardLayout.Instance.getLayout();
                cardLayout.show(ToolBarCardLayout.Instance, "toolbar");
                Safari.Instance.setRoadBuilding(false);
            }
        });
    }
}