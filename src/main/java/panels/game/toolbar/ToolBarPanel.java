package panels.game.toolbar;

import panels.game.toolbar.buttons.shop.*;
import panels.game.toolbar.buttons.speed.SpeedButton;

import javax.swing.*;
import java.awt.*;

/**
 * {@code ToolBarPanel} is the default toolbar panel that provides quick access to entity placement,
 * road tools, and simulation speed control.
 * <p>
 * This panel includes buttons for placing animals, plants, vehicles, water, and other map elements,
 * as well as options for selling and managing road layouts.
 */
public class ToolBarPanel extends JPanel {
    private final RoadTableButton roadTableButton = new RoadTableButton();
    private final SpeedButton speedButton = new SpeedButton();

    /**
     * Constructs the {@code ToolBarPanel} and initializes all toolbar buttons,
     * layout, and visual styling.
     */
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

    /**
     * Returns the {@code SpeedButton} component that controls simulation speed.
     *
     * @return the speed button
     */
    public SpeedButton getSpeedButton() {
        return speedButton;
    }

    /**
     * Overrides the layout manager's behavior to update the position of the
     * speed and road table buttons based on the current panel size.
     */
    @Override
    public void doLayout() {
        super.doLayout();
        speedButton.updatePosition();
        roadTableButton.updatePosition();
    }
}
