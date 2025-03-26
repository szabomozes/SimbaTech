package panels;


import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window() {
        super("Simbatech");
        SwingUtilities.invokeLater(() -> {
            setSize(1200, 800);
            setMinimumSize(new Dimension(800, 600));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            add(CardPanel.Instance);
            //pack();
            setVisible(true);
        });
    }
}
