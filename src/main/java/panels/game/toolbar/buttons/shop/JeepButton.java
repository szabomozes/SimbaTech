package panels.game.toolbar.buttons.shop;

import core.Resources;
import map.EntityCreate;
import panels.feedback.MessageFeedBackPanel;
import panels.game.EventPanel;
import panels.game.toolbar.ToolBarCardLayout;
import safari.Prices;
import safari.Safari;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * JeepButton is a toolbar button that allows the player
 * to purchase and place a jeep on the map.
 *
 * When clicked, it checks if the player has enough coins.
 * If the player can afford the jeep, it deducts the cost and
 * places the jeep at the map's entry point.
 * Otherwise, it displays an error message.
 */
public class JeepButton extends JButton {

    private final int x = 290;
    private final int y = 80;

    /**
     * Constructs a JeepButton with predefined icon and position.
     * Sets up the click logic to handle purchasing a jeep.
     */
    public JeepButton() {
        ImageIcon icon = new ImageIcon(Resources.Instance.jeepButton);
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
                int limit = (int) Prices.getPriceByEnum(Prices.JEEP);
                if (Safari.Instance.coin >= limit) {
                    Safari.Instance.coin -= limit;
                    Safari.Instance.shopping = "jeep";
                    Safari.Instance.placeSomething(EntityCreate.getEntryX(), EntityCreate.getEntryY());
                } else {
                    ToolBarCardLayout.Instance.showCard("void");
                    ((EventPanel) getParent().getParent().getParent().getComponent(0))
                            .setFeedback(new MessageFeedBackPanel("Nincs elegendő pénzed!", "toolbar"));
                }
                getParent().getParent().getParent().getComponent(0).repaint();
            }
        });
    }
}
