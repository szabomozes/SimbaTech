package panels.menu.buttons;

import core.Resources;
import logic.Difficulty;
import logic.Logic;
import map.Create1;
import map.ImageMerger;
import panels.CardPanel;
import panels.game.toolbar.buttons.speed.SpeedButton;

import javax.swing.*;
import java.awt.*;

public class StartButton extends JButton {
    protected Difficulty message;

    public StartButton(String text) {
        super(text);
        setFocusPainted(false);
        setFont(Resources.Instance.menu_font.deriveFont(35f));
        setForeground(Color.BLACK);
    }

    protected void start() {
        Logic.Instance.reset(message);
        SpeedButton.Instance.resetSpeed();
        System.out.println("A " + message.toString() + " gomb meg lett nyomva!");
        System.out.println("Pálya generálás alatt");
        CardPanel.Instance.setLoadingPanel();
        CardPanel.Instance.showCard("loading");

        new Thread(() -> {
            System.out.println("Pálya generálása");
            generateGameBackground();
            Resources.Instance.map();

            SwingUtilities.invokeLater(() -> {
                System.out.println("Pálya betöltve");
                CardPanel.Instance.updateGamePanel();
                CardPanel.Instance.showCard("game");
                CardPanel.Instance.deleteLoadingPanel();
            });
        }).start();
    }

    private void generateGameBackgroundTest() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void generateGameBackground() {
        ImageMerger.newMap(1000);
    }
}
