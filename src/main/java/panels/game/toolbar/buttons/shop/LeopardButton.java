package panels.game.toolbar.buttons.shop;

import core.Resources;

/**
 * LeopardButton is a toolbar button that allows the player
 * to buy and place a leopard in the game.
 *
 * It uses a predefined icon and position on the toolbar,
 * and sets the message identifier to "leopard", which is used
 * by the core logic to determine the purchase type.
 */
public class LeopardButton extends BasicLeftToolBarButton {

    /**
     * Constructs a LeopardButton with the leopard image
     * and a predefined position on the toolbar.
     */
    public LeopardButton() {
        super(Resources.Instance.leopardButton, 80, 10);
        message = "leopard";
    }
}
