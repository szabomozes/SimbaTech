package panels;

import panels.menu.Menu;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window() {
        super("Simbatech");
        SwingUtilities.invokeLater(() -> {
            this.setSize(1200, 800);
            this.setMinimumSize(new java.awt.Dimension(700, 600));
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);

            this.add(CardPanel.Instance);
            this.setVisible(true);
        });
    }
}
