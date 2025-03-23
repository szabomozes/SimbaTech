package panels.game.toolbar.buttons.shop;

import core.Resources;
import panels.game.EventPanel;

public class PanciumButton extends BasicLeftToolBarButton {
    public PanciumButton() {
        super(Resources.Instance.panciumButton, 360, 10);
        message = "pancium";


        addActionListener(e -> {
            boolean currentMode = EventPanel.Instance.isPlacingPanciumMode();
            EventPanel.Instance.setPlacingPanciumMode(!currentMode);
            //EventPanel.Instance.setPlacingPalmTreeMode(false);
            //EventPanel.Instance.setPlacingBaobabMode(false);
            System.out.println("pancium placing mode: " + currentMode);
        });
    }
}
