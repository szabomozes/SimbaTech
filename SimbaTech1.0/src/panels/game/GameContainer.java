package panels.game;


import javax.swing.*;
import java.awt.*;

public class GameContainer extends JPanel {
    public GameContainer() {
        super(new BorderLayout());
        add(new EventPanel(), BorderLayout.CENTER);
    }
}
