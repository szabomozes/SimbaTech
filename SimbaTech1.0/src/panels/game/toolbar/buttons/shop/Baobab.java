package panels.game.toolbar.buttons.shop;

import core.Resources;
import panels.game.EventPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Baobab extends BasicLeftToolBarButton {
    public Baobab() {
        super(Resources.Instance.baobabButton, 220, 10);
        message = "baobab";

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean currentMode = EventPanel.Instance.isPlacingBaobabMode();
                EventPanel.Instance.setPlacingBaobabMode(!currentMode);
                //EventPanel.Instance.setPlacingPalmTreeMode(false);
                //EventPanel.Instance.setPlacingPanciumMode(false);
                System.out.println("Baobab placing mode: " + currentMode + " -> " + !currentMode);
            }
        });
    }
}
