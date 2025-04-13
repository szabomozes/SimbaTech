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

/**
 * {@code DeleteButton} is a custom toolbar button used during road building mode.
 *
 * When clicked, it cancels road building, clears temporary paths,
 * resets the toolbar, and triggers a repaint of the game container.
 */
public class DeleteButton extends JButton {

    /** Icon image for the delete button. */
    private BufferedImage image = Resources.Instance.deleteButton;

    /** X-coordinate for button placement. */
    private final int x = 600;

    /** Y-coordinate for button placement. */
    private final int y = 45;

    /**
     * Constructs a new {@code DeleteButton} with a predefined icon and behavior.
     *
     * The button cancels the road building mode, resets the toolbar, and repaints the main game container.
     */
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
                ToolBarCardLayout.Instance.showCard("toolbar");
                Safari.Instance.clearTempPaths();
                Safari.Instance.setRoadBuilding(false);
                ToolBarCardLayout.Instance.resetToToolbar();
                ((GameContainer) getParent().getParent().getParent()).repaint();
            }
        });
    }
}
