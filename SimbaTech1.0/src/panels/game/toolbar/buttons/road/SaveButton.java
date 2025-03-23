package panels.game.toolbar.buttons.road;

import core.Resources;
import panels.game.toolbar.buttons.shop.BasicLeftToolBarButton;

public class SaveButton extends BasicLeftToolBarButton {
    public SaveButton() {
        super(Resources.Instance.saveButton, 500, 45);
        message = "save";
    }
}
