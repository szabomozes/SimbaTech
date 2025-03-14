package panels;


import panels.game.GameContainer;
import panels.menu.Menu;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {

    public static CardPanel Instance = new CardPanel();

    private CardPanel() {
        super(new CardLayout());
        add(new Menu(), "menu");

    }

    public void showCard(String name) {
        ((CardLayout) getLayout()).show(this, name);
    }

    public void addNewGameLayout() {
        add(new GameContainer(), "game");
    }
}
