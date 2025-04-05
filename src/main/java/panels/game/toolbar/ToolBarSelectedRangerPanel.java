package panels.game.toolbar;

import panels.game.toolbar.buttons.speed.SpeedButton;

import javax.swing.*;
import java.awt.*;

public class ToolBarSelectedRangerPanel extends JPanel {
    public ToolBarSelectedRangerPanel() {
        setLayout(null);

        add(SpeedButton.Instance);

        setBackground(Color.DARK_GRAY);
        setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
    }

    @Override
    public void doLayout() {
        super.doLayout();
        SpeedButton.Instance.updatePosition();
    }
}
