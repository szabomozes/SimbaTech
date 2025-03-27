package panels.menu.buttons;

import core.Resources;
import safari.DifficultyEnum;
import safari.Safari;
import map.ImageMerger;
import panels.CardPanel;
import panels.game.toolbar.ToolBarCardLayout;
import panels.game.toolbar.buttons.speed.SpeedButton;

import javax.swing.*;
import java.awt.*;

public class StartButton extends JButton {
    protected DifficultyEnum message;

    public StartButton(String text) {
        super(text);
        setFocusPainted(false);
        setFont(Resources.Instance.menu_font.deriveFont(35f));
        setForeground(Color.BLACK);
    }

    protected void start() {
        System.out.println("A " + message.toString() + " gomb meg lett nyomva!");
        System.out.println("Pálya generálás alatt");
        CardPanel.Instance.setLoadingPanel();
        CardPanel.Instance.showCard("loading");

        new Thread(() -> {
            System.out.println("Pálya generálása");
            generateGameBackgroundTest();
            Resources.Instance.map();

            SwingUtilities.invokeLater(() -> {
                Safari.Instance.reset(message);
                SpeedButton.Instance.resetSpeed();
                ToolBarCardLayout.Instance.showCard("toolbar");
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
