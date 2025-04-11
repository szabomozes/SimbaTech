package panels.game.toolbar.buttons;

import panels.game.toolbar.buttons.speed.SpeedButton;

import javax.swing.*;
import java.awt.*;

public class ToolBarSelectedRangerPanel extends JPanel {
    private final SpeedButton speedButton = new SpeedButton();


    public ToolBarSelectedRangerPanel() {
        setLayout(null);
        add(speedButton);

        setBackground(Color.DARK_GRAY);
        setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));

    }

    @Override
    public void doLayout() {
        super.doLayout();
        speedButton.updatePosition();
    }
}
