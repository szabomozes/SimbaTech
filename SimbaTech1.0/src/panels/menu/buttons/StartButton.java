package panels.menu.buttons;

import logic.Logic;
import panels.CardPanel;
import panels.game.toolbar.buttons.speed.SpeedButton;

import javax.swing.*;

public class StartButton extends JButton {
    public StartButton(String text) {
        super(text);
    }

    protected void start(String text) {
        Logic.Instance.reset();
        SpeedButton.Instance.resetSpeed();
        System.out.println("A " + text + " gomb meg lett nyomva!");
        System.out.println("Pálya generálás alatt");
        CardPanel.Instance.setLoadingPanel();
        CardPanel.Instance.showCard("loading");

        new Thread(() -> {
            System.out.println("Pálya generálása");
            generateGameBackground();

            SwingUtilities.invokeLater(() -> {
                System.out.println("Pálya betöltve");
                CardPanel.Instance.updateGamePanel();
                CardPanel.Instance.showCard("game");
                CardPanel.Instance.deleteLoadingPanel();
            });
        }).start();
    }

    private void generateGameBackground() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
