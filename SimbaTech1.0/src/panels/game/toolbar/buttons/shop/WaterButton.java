package panels.game.toolbar.buttons.shop;

import core.Resources;

public class WaterButton extends BasicLeftToolBarButton {
    public WaterButton() {
        super(Resources.Instance.waterButton, 220, 80);
        message = "water";
    }
}
