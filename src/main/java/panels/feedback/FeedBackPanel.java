package panels.feedback;

import core.Resources;

import javax.swing.*;
import java.awt.*;

/**
 * {@code FeedBackPanel} is a GUI component that displays different
 * game outcome or error messages to the user with an associated image and confirmation button.
 */
public class FeedBackPanel extends JPanel {
    private JLabel messageLabel;
    private JButton actionButton;
    private JLabel imageLabel;

    /**
     * Constructs a {@code FeedBackPanel} with default layout and components.
     * Initially, the panel is not visible.
     */
    public FeedBackPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 200));
        setBorder(BorderFactory.createEmptyBorder(40,20, 10, 20));

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.NORTH);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        add(messageLabel, BorderLayout.CENTER);

        actionButton = new JButton("Rendben");
        actionButton.addActionListener(e -> setVisible(false));
        add(actionButton, BorderLayout.SOUTH);

        setVisible(false);
    }

    /**
     * Displays a message and image indicating that the player does not have enough money.
     */
    public void notEnoughMoney() {
        messageLabel.setText("<html><center>Nem tudod ezt megvásárolni!<br>Nincs elég pénzed</center></html>");
        imageLabel.setIcon(new ImageIcon(Resources.Instance.badFeddBack));
        setVisible(true);
    }

    /**
     * Displays a generic lose message with a corresponding image.
     */
    public void lose() {
        messageLabel.setText("<html><center>Veszítettél!<br>El vagy tanácsolva!</center></html>");
        imageLabel.setIcon(new ImageIcon(Resources.Instance.loseFeddBack));
        setVisible(true);
    }

    /**
     * Displays a lose message indicating the reason is bankruptcy.
     */
    public void loseByMoney() {
        messageLabel.setText("<html><center>Veszítettél!<br>El vagy tanácsolva!<br>Oka: Csődbe mentél!</center></html>");
        imageLabel.setIcon(new ImageIcon(Resources.Instance.loseFeddBack));
        setVisible(true);
    }

    /**
     * Displays a lose message indicating all animals are extinct.
     */
    public void loseByAnimals() {
        messageLabel.setText("<html><center>Veszítettél!<br>El vagy tanácsolva!<br>Oka: Kipusztult az összes állat!</center></html>");
        imageLabel.setIcon(new ImageIcon(Resources.Instance.loseFeddBack));
        setVisible(true);
    }

    /**
     * Displays a lose message indicating failure to meet required thresholds.
     */
    public void loseByLimit() {
        messageLabel.setText("<html><center>Veszítettél!<br>El vagy tanácsolva!<br>Oka: Nem sikerült a küszöbértékek teljesítése!</center></html>");
        imageLabel.setIcon(new ImageIcon(Resources.Instance.loseFeddBack));
        setVisible(true);
    }

    /**
     * Displays a win message with a corresponding image.
     */
    public void win() {
        messageLabel.setText("<html><center>Gratulálok a győzelemhez!<br>Csodás munkát végeztél!</center></html>");
        imageLabel.setIcon(new ImageIcon(Resources.Instance.winFeddBack));
        setVisible(true);
    }
}
