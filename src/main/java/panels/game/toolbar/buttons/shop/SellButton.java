package panels.game.toolbar.buttons.shop;

import core.Resources;

public class SellButton extends BasicLeftToolBarButton {
    public SellButton() {
        super(Resources.Instance.sellButton, 500, 45);
        message = "sell";
    }
}
