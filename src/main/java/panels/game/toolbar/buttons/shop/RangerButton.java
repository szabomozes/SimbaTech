package panels.game.toolbar.buttons.shop;

import core.Resources;

/**
 * Represents a toolbar button for purchasing and placing a ranger in the game.
 * Uses the predefined ranger image and a fixed position on the toolbar.
 * The message is set to "ranger" to indicate the type of entity being placed.
 */
public class RangerButton extends BasicLeftToolBarButton {

    /**
     * Constructs a RangerButton with the ranger image and predefined coordinates.
     * Sets the purchase message to "ranger".
     */
    public RangerButton() {
        super(Resources.Instance.rangerButton, 360, 80);
        message = "ranger";
    }
}
