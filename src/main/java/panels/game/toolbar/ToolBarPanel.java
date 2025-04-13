package panels.game.toolbar;

import panels.game.toolbar.buttons.shop.*;
import panels.game.toolbar.buttons.speed.SpeedButton;

import javax.swing.*;
import java.awt.*;

public class ToolBarPanel extends JPanel {
    private final RoadTableButton roadTableButton = new RoadTableButton();
    private final SpeedButton speedButton = new SpeedButton();


    public ToolBarPanel() {
        setLayout(null);

        add(new LionButton());
        add(new LeopardButton());
        add(new ZebraButton());
        add(new GiraffeButton());

        add(new BaobabButton());
        add(new PalmTreeButton());
        add(new PanciumButton());
        add(new WaterButton());
        add(new JeepButton());
        add(new RangerButton());

        add(new SellButton());

        add(roadTableButton);
        add(speedButton);

        setBackground(new Color(40, 40, 40));
        setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));

    }

    public SpeedButton getSpeedButton() {
        return speedButton;
    }

    @Override
    public void doLayout() {
        super.doLayout();
        speedButton.updatePosition();
        roadTableButton.updatePosition();
    }
}
