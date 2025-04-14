package panels;

import core.Resources;

import javax.swing.*;
import java.awt.*;

/**
 * {@code Window} is the main application frame for the Simbatech program.
 * It initializes the window with a fixed minimum size, sets the icon and title,
 * and displays the main {@code CardPanel} containing all the UI screens.
 */
public class Window extends JFrame {

    /**
     * Constructs the main application window, sets its properties,
     * and displays the {@code CardPanel}.
     */
    public Window() {
        super("Simbatech");
        SwingUtilities.invokeLater(() -> {
            setMinimumSize(new Dimension(1000, 600));
            setSize(1000, 700); // Initial size (can be adjusted)
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null); // Center on screen
            setIconImage(Resources.Instance.mainlogo);
            add(CardPanel.Instance);
            //pack(); // Optional: calculate size based on contents
            System.out.println(this.getWidth());
            setVisible(true);
        });
    }
}
