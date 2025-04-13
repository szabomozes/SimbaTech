package panels.game.toolbar.buttons.shop;

import core.Resources;

/**
 * GiraffeButton is a toolbar button that allows the player
 * to buy a giraffe in the game.
 *
 * It is placed at a predefined location on the screen and uses
 * a specific image loaded from the game's resources.
 */
public class GiraffeButton extends BasicLeftToolBarButton {

    /**
     * Creates a new GiraffeButton with its icon and screen position.
     * Sets the internal message used to trigger the giraffe buying logic.
     */
    public GiraffeButton() {
        super(Resources.Instance.giraffeButon, 80, 80);
        message = "giraffe";
    }
}
