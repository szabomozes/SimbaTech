package panels.game.toolbar.buttons.shop;

import core.Resources;

/**
 * A toolbar button that activates selling mode in the game.
 * When clicked, it switches the UI to the SellingToolBar and sets the game into selling mode.
 */
public class SellButton extends BasicLeftToolBarButton {

    /**
     * Constructs the SellButton using the predefined image resource and positions it.
     * Sets the button's action message to "sell".
     */
    public SellButton() {
        super(Resources.Instance.sellButton, 500, 45);
        message = "sell";
    }
}
