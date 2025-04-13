package panels.game.toolbar.buttons.shop;

import core.Resources;
import road.Path;
import map.EntityCreate;
import panels.CardPanel;
import panels.game.toolbar.ToolBarCardLayout;
import safari.Safari;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a toolbar button that enables road-building mode in the game.
 * When clicked, it switches the UI to the road-building panel and initializes a new temporary path.
 */
public class RoadTableButton extends JButton {

    private final int x = 120;
    private final int y = 45;

    /**
     * Constructs the RoadTableButton with an icon, styling, and action logic.
     * Positions the button dynamically based on the width of the CardPanel.
     */
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
                System.out.println("road-table");
                ToolBarCardLayout.Instance.showCard("buildRoad");
                Safari.Instance.setRoadBuilding(true);
                Safari.Instance.getTempPaths().add(new Path(EntityCreate.entryX, EntityCreate.entryY));
            }
        });
    }

    /**
     * Updates the position of the button based on the current width of the CardPanel.
     * Should be called when the layout changes.
     */
    public void updatePosition() {
        setBounds(CardPanel.Instance.getWidth() - getWidth() - x, y, getWidth(), getHeight());
    }
}
