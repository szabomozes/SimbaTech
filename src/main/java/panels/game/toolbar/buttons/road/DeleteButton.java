package panels.game.toolbar.buttons.road;

import core.Resources;
import panels.CardPanel;
import panels.game.GameContainer;
import panels.game.toolbar.ToolBarCardLayout;
import safari.Safari;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class DeleteButton extends JButton {
    private BufferedImage image = Resources.Instance.deleteButton;
    private final int x = 600;
    private final int y = 45;

    public DeleteButton() {
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
                CardLayout cardLayout = (CardLayout) ToolBarCardLayout.Instance.getLayout();
                cardLayout.show(ToolBarCardLayout.Instance, "toolbar");
                Safari.Instance.clearTempPaths();
                Safari.Instance.setRoadBuilding(false);
                ToolBarCardLayout.Instance.resetToToolbar();
                ((GameContainer) getParent().getParent().getParent()).repaint();
            }
        });
    }
}