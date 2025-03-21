package panels.game.toolbar;

import javax.swing.*;
import java.awt.*;

public class ToolBarPanelTest extends JPanel {

    public ToolBarPanelTest() {
        setLayout(null);

        add(new LionButton());
        add(new LeopardButton());
        add(new ZebraButton());
        add(new GiraffeButton());

        setPreferredSize(new Dimension(700, 200));


    }
}
