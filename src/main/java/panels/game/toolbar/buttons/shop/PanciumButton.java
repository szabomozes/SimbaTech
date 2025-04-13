package panels.game.toolbar.buttons.shop;

import core.Resources;

/**
 * Represents a toolbar button for purchasing and placing a pancium plant in the game.
 * Uses the predefined pancium image and a fixed position on the toolbar.
 * The message is set to "pancium" to indicate the type of entity being placed.
 */
public class PanciumButton extends BasicLeftToolBarButton {

    /**
     * Constructs a PanciumButton with the pancium image and predefined coordinates.
     * Sets the purchase message to "pancium".
     */
    public PanciumButton() {
        super(Resources.Instance.panciumButton, 360, 10);
        message = "pancium";
    }
}
