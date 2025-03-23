package panels.game.toolbar.buttons.shop;

import core.Resources;
import panels.game.EventPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PalmTreeButton extends BasicLeftToolBarButton {
    public PalmTreeButton() {
        super(Resources.Instance.palmTreeButton, 290, 10);
        message = "palm-tree";

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean currentMode= EventPanel.Instance.isPlacingPalmTreeMode();
                EventPanel.Instance.setPlacingPalmTreeMode(!currentMode);
                //EventPanel.Instance.setPlacingBaobabMode(false);
                //EventPanel.Instance.setPlacingPanciumMode(false);

                System.out.println("palm-tree placing mode: "+currentMode);

            }
        });
    }
}
