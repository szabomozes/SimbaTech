package panels.game.toolbar.buttons;

import core.Resources;
import safari.Safari;
import panels.game.toolbar.ToolBarCardLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * {@code BuyingSellingToolBarBin} is a toolbar button that acts as a "cancel" or "exit" action
 * during buying or selling mode. When clicked, it resets the toolbar back to the default state.
 *
 * The button uses a custom bin icon and resets related flags in the {@code Safari} instance.
 */
public class BuyingSellingToolBarBin extends JButton {

    /**
     * Constructs the {@code BuyingSellingToolBarBin} button with custom styling and click behavior.
     * When clicked, it:
     *     Switches the toolbar view back to the main toolbar
     *     Clears the {@code shopping} object
     *     Disables selling mode
     */
    public BuyingSellingToolBarBin() {
        ImageIcon image = new ImageIcon(Resources.Instance.binButton);
        setIcon(image);

        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ToolBarCardLayout.Instance.showCard("toolbar");
                Safari.Instance.shopping = null;
                Safari.Instance.setSellingMode(false);
            }
        });
    }
}
