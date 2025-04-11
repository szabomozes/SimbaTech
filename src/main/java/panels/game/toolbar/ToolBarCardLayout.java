package panels.game.toolbar;

import panels.game.toolbar.buttons.ToolBarSelectedRangerPanel;

import javax.swing.*;
import java.awt.*;

public class ToolBarCardLayout extends JPanel {

    public static final ToolBarCardLayout Instance = new ToolBarCardLayout();
    private String currentCardName = "toolbar";
    private ToolBarCardLayout() {
        super(new CardLayout());
        add(new ToolBarPanel(), "toolbar");
        add(new RoadBuildingPanel(), "buildRoad");
        add(new BuyingToolBar(), "buying");
        add(new SellingToolBar(), "selling");
        add(new ToolBarFeedBackPanel(), "void");
        add(new ToolBarSelectedRangerPanel(), "selectedRanger");
        setPreferredSize(new Dimension(0, 150));


    }

    public void showCard(String name) {
        if (!name.equals(currentCardName)) {
        }
        ((CardLayout) getLayout()).show(this, name);
        currentCardName = name;
    }

    public String getCurrentCardName() {
        return currentCardName;
    }

    public void resetToToolbar() {
        showCard("toolbar");
    }
}

