package panels.game;

import panels.game.toolbar.ToolBarCardLayout;

import javax.swing.*;
import java.awt.*;

/**
 * GameContainer is the main container panel that holds the primary gameplay interface.
 * It combines the central game area (EventPanel) and the toolbar section (ToolBarCardLayout).
 */
public class GameContainer extends JPanel {

    /**
     * Constructs a new GameContainer using BorderLayout.
     * Adds the EventPanel to the center and the toolbar to the bottom (south).
     */
    public GameContainer() {
        super(new BorderLayout());

        // Main game interaction panel goes in the center
        add(new EventPanel(), BorderLayout.CENTER);

        // Toolbar with game actions goes at the bottom
        add(ToolBarCardLayout.Instance, BorderLayout.SOUTH);
    }
}
