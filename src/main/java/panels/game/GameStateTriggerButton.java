package panels.game;

import core.Resources;
import entity.mobile.animal.*;
import panels.CardPanel;
import panels.feedback.GameStatePanel;
import panels.game.toolbar.ToolBarCardLayout;
import safari.GameStateChecker;
import safari.Safari;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameStateTriggerButton extends JButton {

    public static GameStateTriggerButton Instance = new GameStateTriggerButton();
    private boolean clicked = true;

    public GameStateTriggerButton() {
        ImageIcon icon = new ImageIcon(Resources.Instance.info);
        setIcon(icon);

        int width = icon.getIconWidth();
        int height = icon.getIconHeight();
        setBounds(CardPanel.Instance.getWidth() - width - 70, 10, width, height);

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        setRolloverEnabled(false);
        setFocusPainted(false);

        setHorizontalTextPosition(JButton.CENTER);
        setVerticalTextPosition(JButton.CENTER);
        setFont(Resources.Instance.menu_font.deriveFont(35f));
        setForeground(Color.BLACK);

        setEnabled(clicked);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Safari.Instance.isSelectedRanger()) {
                    return;
                }
                try {
                    setClicked(false);
                    Container parent = getParent();
                    while (parent != null && !(parent instanceof EventPanel)) {
                        parent = parent.getParent();
                    }

                    if (parent != null) {
                        int herbivoreCount = (int) Safari.Instance.getAnimals().stream()
                                .filter(a -> a instanceof Zebra || a instanceof Giraffe)
                                .count();

                        int carnivoreCount = (int) Safari.Instance.getAnimals().stream()
                                .filter(a -> a instanceof Lion || a instanceof Leopard)
                                .count();

                        int visitorCount = Safari.Instance.getPassengers();

                        int balance = Safari.Instance.coin;

                        GameStateChecker checker = Safari.Instance.getGameStateChecker();
                        int herbivoreThreshold = checker.getHerbivoreThreshold();
                        int predatorThreshold = checker.getPredatorThreshold();
                        int visitorThreshold = checker.getVisitorThreshold();
                        int coinThreshold = checker.getCoinThreshold();

                        String herbivoreColor = herbivoreCount >= herbivoreThreshold ? "green" : "red";
                        String carnivoreColor = carnivoreCount >= predatorThreshold ? "green" : "red";
                        String visitorColor = visitorCount >= visitorThreshold ? "green" : "red";
                        String balanceColor = balance >= coinThreshold ? "green" : "red";

                        String gameStateText = String.format(
                                "<html>" +
                                        "<table>" +
                                        "<tr><td align='left'>növényevő:</td><td align='right'><font color='%s'>%d</font></td><td align='left'> | %d</td></tr>" +
                                        "<tr><td align='left'>húsevő:</td><td align='right'><font color='%s'>%d</font></td><td align='left'> | %d</td></tr>" +
                                        "<tr><td align='left'>látogató:</td><td align='right'><font color='%s'>%d</font></td><td align='left'> | %d</td></tr>" +
                                        "<tr><td align='left'>egyenleg:</td><td align='right'><font color='%s'>%d</font></td><td align='left'> | %d</td></tr>" +
                                        "</table>" +
                                        "</html>",
                                herbivoreColor, herbivoreCount, herbivoreThreshold,
                                carnivoreColor, carnivoreCount, predatorThreshold,
                                visitorColor, visitorCount, visitorThreshold,
                                balanceColor, balance, coinThreshold
                        );

                        String currentMode = ToolBarCardLayout.Instance.getCurrentCardName();
                        GameStatePanel gameStatePanel = new GameStatePanel(gameStateText, currentMode);
                        ((EventPanel) parent).setFeedback(gameStatePanel);
                        parent.repaint();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
        setEnabled(clicked && !Safari.Instance.isSelectedRanger());
    }

    public void updatePosition() {
        setBounds(CardPanel.Instance.getWidth() - getWidth() - 70, 10, getWidth(), getHeight());
    }

    public void updateButtonState() {
        setEnabled(clicked && !Safari.Instance.isSelectedRanger());
    }


}