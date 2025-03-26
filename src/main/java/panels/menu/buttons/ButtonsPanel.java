package panels.menu.buttons;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {
    public ButtonsPanel() {
        setOpaque(false);
        setLayout(new FlowLayout());

        JButton easyButton = new EasyButton();
        JButton mediumButton = new MediumButton();
        JButton hardButton = new HardButton();

        add(easyButton);
        add(mediumButton);
        add(hardButton);
    }
}
