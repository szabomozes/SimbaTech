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

    public void setLoadingPanel() {
        Component loadingPanel = getComponent("loading");
        if (loadingPanel != null) {
            remove(loadingPanel);
        }

        add(new LoadingPanel(), "loading");
    }

    public void deleteLoadingPanel(){
        Component loadingPanel = getComponent("loading");
        if (loadingPanel != null) {
            remove(loadingPanel);
        }
    }

    public void updateGamePanel() {
        Component gamePanel = getComponent("game");
        if (gamePanel != null) {
            remove(gamePanel);
        }

        add(new GameContainer(), "game");
    }

    private Component getComponent(String name) {
        for (Component comp : getComponents()) {
            if (name.equals(comp.getName())) {
                return comp;
            }
        }
        return null;
    }
}
