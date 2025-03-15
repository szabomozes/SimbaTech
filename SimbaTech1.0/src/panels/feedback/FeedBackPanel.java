package panels.feedback;

import core.Resources;

import javax.swing.*;
import java.awt.*;

public class FeedBackPanel extends JPanel {
    private JLabel messageLabel;
    private JButton actionButton;
    private JLabel imageLabel;

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

    public void notEnoughMoney() {
        messageLabel.setText("<html><center>Nem tudod ezt megvásárolni!<br>Nincs elég pénzed</center></html>");
        imageLabel.setIcon(new ImageIcon(Resources.Instance.badFeddBack));
        setVisible(true);
    }

    public void lose() {
        messageLabel.setText("<html><center>Veszítettél!<br>El vagy tanácsolva!</center></html>");
        imageLabel.setIcon(new ImageIcon(Resources.Instance.loseFeddBack));
        setVisible(true);
    }

    public void loseByMoney() {
        messageLabel.setText("<html><center>Veszítettél!<br>El vagy tanácsolva!<br>Oka: Csődbe mentél!</center></html>");
        imageLabel.setIcon(new ImageIcon(Resources.Instance.loseFeddBack));
        setVisible(true);
    }

    public void loseByAnimals() {
        messageLabel.setText("<html><center>Veszítettél!<br>El vagy tanácsolva!<br>Oka: Kipusztult az összes állat!</center></html>");
        imageLabel.setIcon(new ImageIcon(Resources.Instance.loseFeddBack));
        setVisible(true);
    }

    public void loseByLimit() {
        messageLabel.setText("<html><center>Veszítettél!<br>El vagy tanácsolva!<br>Oka: Nem sikerült a küszöbértékek teljesítése!</center></html>");
        imageLabel.setIcon(new ImageIcon(Resources.Instance.loseFeddBack));
        setVisible(true);
    }

    public void win() {
        messageLabel.setText("<html><center>Gratulálok a győzelemhez!<br>Csodás munkát végeztél!</center></html>");
        imageLabel.setIcon(new ImageIcon(Resources.Instance.winFeddBack));
        setVisible(true);
    }



}
