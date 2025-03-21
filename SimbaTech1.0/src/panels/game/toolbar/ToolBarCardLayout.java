package panels.game.toolbar;

import javax.swing.*;
import java.awt.*;

public class ToolBarCardLayout extends JPanel {

    public static final ToolBarCardLayout Instance = new ToolBarCardLayout();

    private ToolBarCardLayout() {
        super(new CardLayout());
        add(new ToolBarPanelTest(), "toolbar");
        setPreferredSize(new Dimension(0, 150));

    }
}
