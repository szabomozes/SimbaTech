package panels.game.toolbar;

import javax.swing.*;
import java.awt.*;

public class ToolBarCardLayout extends JPanel {

    public static final ToolBarCardLayout Instance = new ToolBarCardLayout();

    private ToolBarCardLayout() {
        super(new CardLayout());
        add(new ToolBarPanel(), "toolbar");
        add(new BuildRoadTool(), "buildRoad");
        add(new BuyingToolBar(), "buying");
        setPreferredSize(new Dimension(0, 150));


    }

    public void showCard(String name) {
        ((CardLayout) getLayout()).show(this, name);
    }
}
