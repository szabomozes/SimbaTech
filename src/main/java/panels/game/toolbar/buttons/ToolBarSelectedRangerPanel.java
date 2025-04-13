package panels.game.toolbar.buttons;

import panels.game.toolbar.buttons.speed.SpeedButton;

import javax.swing.*;
import java.awt.*;

/**
 * {@code ToolBarSelectedRangerPanel} is a specialized toolbar panel displayed when a ranger is selected.
 *
 * It contains a {@link SpeedButton} to control the ranger's speed.
 */
public class ToolBarSelectedRangerPanel extends JPanel {

    /** Button used to control the speed of the selected ranger. */
    private final SpeedButton speedButton = new SpeedButton();

    /**
     * Constructs the {@code ToolBarSelectedRangerPanel}, initializing its layout and appearance.
     * The panel uses a dark background and a top black border, and includes the speed button.
     */
    public ToolBarSelectedRangerPanel() {
        setLayout(null);
        add(speedButton);

        setBackground(Color.DARK_GRAY);
        setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
    }

    /**
     * Lays out the components within this panel. Specifically, updates the position of the {@code speedButton}.
     */
    @Override
    public void doLayout() {
        super.doLayout();
        speedButton.updatePosition();
    }
}
