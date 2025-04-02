package panels.game.toolbar.buttons.shop;

import core.Resources;
import panels.CardPanel;
import panels.game.toolbar.ToolBarCardLayout;
import safari.Safari;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoadTableButton extends JButton {

    private final int x = 120;
    private final int y = 45;

    public RoadTableButton() {
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);

        ImageIcon icon = new ImageIcon(Resources.Instance.roadTableButton);
        setIcon(icon);

        int width = icon.getIconWidth();
        int height = icon.getIconHeight();
        setBounds(CardPanel.Instance.getWidth() - width - x, y, width, height);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("raod-table");
                ToolBarCardLayout.Instance.showCard("buildRoad");
                Safari.Instance.setRoadBuilding(true);
            }
        });
    }
    public void updatePosition() {
        setBounds(CardPanel.Instance.getWidth() - getWidth() - x, y, getWidth(), getHeight());
    }
}
