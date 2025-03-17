package panels.game;


import javax.swing.*;
import java.awt.*;

public class GameContainer extends JPanel {
    private EventPanel eventPanel;
    private ToolBarPanel toolPanel;

    public GameContainer() {
        super(new BorderLayout());
        eventPanel = new EventPanel();
        add(eventPanel, BorderLayout.CENTER);
        toolPanel = new ToolBarPanel();

        add(toolPanel, BorderLayout.SOUTH);
    }

    public void toggleFullImage(boolean full) {
        eventPanel.toggleFullImage(full); // Meghívás
    }
}
