package panels.game.toolbar.buttons.shop;

import core.Resources;

/**
 * {@code BaobabButton} represents a toolbar button used to select the Baobab tree for placement in the game.
 *
 * This button is positioned on the left side of the toolbar and uses a custom icon from the {@link Resources} class.
 * When clicked, it likely triggers the selection of the Baobab item for purchase or placement in the game world.
 */
public class BaobabButton extends BasicLeftToolBarButton {

    /**
     * Constructs a {@code BaobabButton} with a predefined icon and position.
     *
     * The icon is loaded from {@link Resources#baobabButton}, and the button is placed
     * at coordinates (220, 10) on the toolbar.
     */
    public BaobabButton() {
        super(Resources.Instance.baobabButton, 220, 10);
        message = "baobab";
    }
}
