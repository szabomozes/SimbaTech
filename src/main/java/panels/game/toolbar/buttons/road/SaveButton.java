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

/**
 * {@code SaveButton} is a custom button used to confirm road construction in the game.
 *
 * When clicked, it either completes the road construction process if all temporary paths are valid,
 * or displays a feedback message if some roads are not finalized or the player doesn't have enough coins.
 */
public class SaveButton extends JButton {

    /** Icon image for the save button. */
    private BufferedImage image = Resources.Instance.saveButton;

    /** X-coordinate for button placement. */
    private final int x = 500;

    /** Y-coordinate for button placement. */
    private final int y = 45;

    /**
     * Constructs a new {@code SaveButton} with an icon and a specific action.
     *
     * This action checks for unfinished roads, validates the player's coin balance,
     * adds completed paths to the game, and resets the road building mode.
     * If requirements aren't met, it shows feedback through the {@link MessageFeedBackPanel}.
     */
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
                ToolBarCardLayout.Instance.showCard("toolbar");

                // Check if the last path has any unfinished roads
                if (!Safari.Instance.getTempPaths().getLast().getRoads().isEmpty()) {
                    ((EventPanel) getParent().getParent().getParent().getComponent(0))
                            .setFeedback(new MessageFeedBackPanel("Finish all roads before purchasing!", "buildRoad"));
                } else {
                    int price = Safari.Instance.getTempPathsPrice();
                    if (price <= Safari.Instance.coin) {
                        // Add all completed paths except the last temporary one
                        for (int i = 0; i < Safari.Instance.getTempPaths().size() - 1; i++) {
                            Safari.Instance.addAPathToPaths(Safari.Instance.getTempPaths().get(i));
                        }
                        Safari.Instance.coin -= price;
                        Safari.Instance.setRoadBuilding(false);
                        Safari.Instance.clearTempPaths();
                    } else {
                        ((EventPanel) getParent().getParent().getParent().getComponent(0))
                                .setFeedback(new MessageFeedBackPanel(price + "$ is required for the construction!", "buildRoad"));
                    }
                }

                ((GameContainer) getParent().getParent().getParent()).repaint();
            }
        });
    }
}
