package panels.game.toolbar.buttons.shop;

import core.Resources;

/**
 * A toolbar button that allows the player to purchase or place a water entity in the game.
 * Inherits functionality from {@link BasicLeftToolBarButton}.
 */
public class WaterButton extends BasicLeftToolBarButton {

    /**
     * Constructs the WaterButton using the predefined image resource and places it at a specific position.
     * Sets the message used for interaction to "water".
     */
    public WaterButton() {
        super(Resources.Instance.waterButton, 220, 80);
        message = "water";
    }
}
