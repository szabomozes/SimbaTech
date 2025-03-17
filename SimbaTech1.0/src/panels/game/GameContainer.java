package panels.game;


import javax.swing.*;
import java.awt.*;

public class GameContainer extends JPanel {
    private EventPanel eventPanel;

    public GameContainer() {
        super(new BorderLayout());
        eventPanel = new EventPanel();
        add(eventPanel, BorderLayout.CENTER);
    }

    public void toggleFullImage(boolean full) {
        eventPanel.toggleFullImage(full); // Meghívás
    }
}
