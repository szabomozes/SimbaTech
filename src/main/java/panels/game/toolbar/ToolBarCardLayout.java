package panels.game.toolbar;

import panels.game.toolbar.buttons.ToolBarSelectedRangerPanel;

import javax.swing.*;
import java.awt.*;

/**
 * {@code ToolBarCardLayout} is a singleton panel that manages multiple toolbar views using a {@code CardLayout}.
 * It allows switching between different toolbar modes such as building roads, buying, selling, and viewing selected rangers.
 */
public class ToolBarCardLayout extends JPanel {

    public static final ToolBarCardLayout Instance = new ToolBarCardLayout();
    private String currentCardName = "toolbar";
    private ToolBarPanel toolBarPanel;

    /**
     * Private constructor for the {@code ToolBarCardLayout} singleton instance.
     * Initializes and adds various toolbar-related panels to the layout.
     */
    private ToolBarCardLayout() {
        super(new CardLayout());
        toolBarPanel = new ToolBarPanel();
        add(toolBarPanel, "toolbar");
        add(new RoadBuildingPanel(), "buildRoad");
        add(new BuyingToolBar(), "buying");
        add(new SellingToolBar(), "selling");
        add(new ToolBarFeedBackPanel(), "void");
        add(new ToolBarSelectedRangerPanel(), "selectedRanger");
        setPreferredSize(new Dimension(0, 150));
    }

    /**
     * Displays the panel associated with the given card name.
     *
     * @param name the name of the panel to display
     */
    public void showCard(String name) {
        if (!name.equals(currentCardName)) {
            ((CardLayout) getLayout()).show(this, name);
            currentCardName = name;
        }
    }

    /**
     * Returns the name of the currently displayed panel.
     *
     * @return the current card name
     */
    public String getCurrentCardName() {
        return currentCardName;
    }

    /**
     * Resets the toolbar to its default state by showing the main toolbar
     * and resetting the speed button.
     */
    public void resetToToolbar() {
        toolBarPanel.getSpeedButton().resetSpeed();
        showCard("toolbar");
    }
}
