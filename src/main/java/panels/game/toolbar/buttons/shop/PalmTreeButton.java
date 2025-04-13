package panels.game.toolbar.buttons.shop;

import core.Resources;

/**
 * Represents a toolbar button for purchasing and placing a palm tree in the game.
 * Uses the predefined palm tree icon and fixed position on the toolbar.
 * The associated message is set to "palmtree" to identify the entity type.
 */
public class PalmTreeButton extends BasicLeftToolBarButton {

    /**
     * Constructs a PalmTreeButton with the palm tree image and predefined coordinates.
     * Sets the purchase message to "palmtree".
     */
    public PalmTreeButton() {
        super(Resources.Instance.palmTreeButton, 290, 10);
        message = "palmtree";
    }
}
