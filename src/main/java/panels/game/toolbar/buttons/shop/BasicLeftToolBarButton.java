package panels.game.toolbar.buttons.shop;

import panels.game.toolbar.SellingToolBar;
import panels.game.toolbar.ToolBarCardLayout;
import safari.Safari;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * BasicLeftToolBarButton is an abstract class that defines the common behavior
 * of toolbar buttons placed on the left side of the game UI.
 *
 * These buttons are associated with buying or selling specific items in the game.
 * Subclasses must define the specific icon and position of the button,
 * and optionally override the default behavior.
 */
public abstract class BasicLeftToolBarButton extends JButton {

    /**
     * The internal message or item identifier used to perform actions when the button is clicked.
     */
    protected String message;

    /**
     * Constructs a BasicLeftToolBarButton with the specified image and position.
     *
     * @param image the icon image to display on the button
     * @param x     the x-coordinate of the button's position
     * @param y     the y-coordinate of the button's position
     */
    public BasicLeftToolBarButton(BufferedImage image, int x, int y) {
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
                if (message.equals("sell")) {
                    new SellingToolBar(); // This line seems redundant, as the created panel is not stored or used
                    ToolBarCardLayout.Instance.showCard("selling");
                    Safari.Instance.setSellingMode(true);
                } else {
                    Safari.Instance.buySomething(message);
                }
            }
        });
    }
}
