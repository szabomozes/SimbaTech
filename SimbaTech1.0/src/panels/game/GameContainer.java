package panels.game;


import panels.game.toolbar.ToolBarCardLayout;

import javax.swing.*;
import java.awt.*;

public class GameContainer extends JPanel {
    public GameContainer() {
        super(new BorderLayout());
        add(EventPanel.Instance, BorderLayout.CENTER);
        EventPanel.Instance.setClearArrays();
        add(ToolBarCardLayout.Instance, BorderLayout.SOUTH);
    }
}
