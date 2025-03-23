package panels.game.toolbar.buttons.shop;

import core.Resources;
import panels.game.EventPanel;

public class WaterButton extends BasicLeftToolBarButton {
    public WaterButton() {
        super(Resources.Instance.waterButton, 220, 80);
        message = "water";


        addActionListener(e -> {
            boolean currentMode = EventPanel.Instance.isPlacingWaterMode();
            EventPanel.Instance.setPlacingWaterMode(!currentMode);
            System.out.println("water placing mode: " + currentMode);
        });
    }
}
