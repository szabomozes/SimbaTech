package panels.game.toolbar;

import panels.game.toolbar.buttons.shop.*;
import panels.game.toolbar.buttons.speed.SpeedButton;

import javax.swing.*;

public class ToolBarPanelTest extends JPanel {
    private final RoadTableButton roadTableButton = new RoadTableButton();

    public ToolBarPanelTest() {
        setLayout(null);

        add(new LionButton());
        add(new LeopardButton());
        add(new ZebraButton());
        add(new GiraffeButton());

        add(new Baobab());
        add(new PalmTreeButton());
        add(new PanciumButton());
        add(new WaterButton());
        add(new JeepButton());
        add(new RangerButton());

        add(new SellButton());

        add(roadTableButton);
        add(SpeedButton.Instance);


    }

    @Override
    public void doLayout() {
        super.doLayout();
        SpeedButton.Instance.updatePosition();
        roadTableButton.updatePosition();
    }
}
