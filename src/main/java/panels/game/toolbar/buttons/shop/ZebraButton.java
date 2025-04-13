package panels.game.toolbar.buttons.shop;

import core.Resources;

/**
 * A toolbar button that allows the player to purchase or place a zebra entity in the game.
 * Inherits functionality from {@link BasicLeftToolBarButton}.
 */
public class ZebraButton extends BasicLeftToolBarButton {

    /**
     * Constructs the ZebraButton using the predefined zebra image resource
     * and sets its position on the toolbar. Sets the internal message to "zebra".
     */
    public ZebraButton() {
        super(Resources.Instance.zebraButton, 10, 80);
        message = "zebra";
    }
}
