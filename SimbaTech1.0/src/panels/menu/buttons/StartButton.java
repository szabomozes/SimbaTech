package panels.menu.buttons;

import panels.CardPanel;

import javax.swing.*;

public class StartButton extends JButton {
    public StartButton(String text) {
        super(text);
    }
    protected void start(String text) {
        SwingUtilities.invokeLater(() -> {
            System.out.println("A "+ text +" gomb meg lett nyomva!");
            System.out.println("Pálya generálás alatt");
            CardPanel.Instance.addNewGameLayout();
            CardPanel.Instance.showCard("game");
        });
    }
}
