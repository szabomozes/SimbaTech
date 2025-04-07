package panels.game.toolbar.buttons.road;

import core.Resources;
import road.Path;
import panels.feedback.MessageFeedBackPanel;
import panels.game.EventPanel;
import panels.game.GameContainer;
import panels.game.toolbar.ToolBarCardLayout;
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
                CardLayout cardLayout = (CardLayout) ToolBarCardLayout.Instance.getLayout();
                cardLayout.show(ToolBarCardLayout.Instance, "toolbar");

                if (!Safari.Instance.getTempPaths().getLast().getRoads().isEmpty()) {
                    ((EventPanel) getParent().getParent().getParent().getComponent(0)).setFeedback(new MessageFeedBackPanel("Zárd le az összes utat vásárlás előtt!", "buildRoad"));
                } else {
                    int price = Safari.Instance.getTempPathsPrice();
                    System.out.println(price + " $");
                    if (price <= Safari.Instance.coin) {
                        for (int i = 0; i < Safari.Instance.getTempPaths().size() - 1; i++) {
                            Safari.Instance.addAPathToPaths(Safari.Instance.getTempPaths().get(i));
                        }
                        Safari.Instance.coin -= price;
                        Safari.Instance.setRoadBuilding(false);
                        Safari.Instance.clearTempPaths();
                    } else {
                        ((EventPanel) getParent().getParent().getParent().getComponent(0)).setFeedback(new MessageFeedBackPanel(price + "$ kerül az építkezés!", "buildRoad"));
                    }
                }

                ((GameContainer) getParent().getParent().getParent()).repaint();
            }
        });
    }
}