package panels;


import javax.swing.*;

public class Window extends JFrame {
    public Window() {
        super("Simbatech");
        SwingUtilities.invokeLater(() -> {
            setSize(1200, 800);
            setMinimumSize(new java.awt.Dimension(700, 600));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            add(CardPanel.Instance);
            pack();
            setVisible(true);
        });
    }
}
