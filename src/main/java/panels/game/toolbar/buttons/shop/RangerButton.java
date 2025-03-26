package panels.game.toolbar.buttons.shop;

import core.Resources;

public class RangerButton extends BasicLeftToolBarButton {
    public RangerButton() {
        super(Resources.Instance.rangerButton, 360, 80);
        message = "ranger";
    }
}
