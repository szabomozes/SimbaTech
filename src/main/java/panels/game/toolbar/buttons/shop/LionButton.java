package panels.game.toolbar.buttons.shop;

import core.Resources;

/**
 * Represents a toolbar button for purchasing and placing a lion entity in the game.
 * Uses the predefined lion icon and fixed position on the toolbar.
 * The associated message is set to "lion" to indicate the entity type for purchasing.
 */
public class LionButton extends BasicLeftToolBarButton {

    /**
     * Constructs a LionButton with the lion image and predefined coordinates.
     * Sets the purchase message to "lion".
     */
    public LionButton() {
        super(Resources.Instance.lionButton, 10, 10);
        message = "lion";
    }
}
