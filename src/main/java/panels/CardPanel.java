package panels;

import panels.game.GameContainer;
import panels.menu.MenuPanel;

import javax.swing.*;
import java.awt.*;

/**
 * {@code CardPanel} is a singleton container panel that manages different views (cards)
 * in a card layout. It handles dynamic switching between UI panels such as menu,
 * game, and loading screens.
 */
public class CardPanel extends JPanel {

    public static final CardPanel Instance = new CardPanel();

    /**
     * Private constructor to enforce singleton pattern.
     * Initializes the panel with a CardLayout and adds the menu panel.
     */
    private CardPanel() {
        super(new CardLayout());
        add(new MenuPanel(), "menu");
    }

    /**
     * Switches the visible panel to the one identified by the given name.
     *
     * @param name the name of the card to show
     */
    public void showCard(String name) {
        ((CardLayout) getLayout()).show(this, name);
    }

    /**
     * Adds or replaces the loading panel in the card layout.
     * If a loading panel already exists, it will be removed first.
     */
    public void setLoadingPanel() {
        Component loadingPanel = getComponent("loading");
        if (loadingPanel != null) {
            remove(loadingPanel);
        }

        add(new LoadingPanel(), "loading");
    }

    /**
     * Removes the loading panel from the card layout if it exists.
     */
    public void deleteLoadingPanel(){
        Component loadingPanel = getComponent("loading");
        if (loadingPanel != null) {
            remove(loadingPanel);
        }
    }

    /**
     * Removes the current game panel if present, then adds a new instance
     * of {@code GameContainer} to the card layout.
     */
    public void updateGamePanel() {
        Component gamePanel = getComponent("game");
        if (gamePanel != null) {
            remove(gamePanel);
        }

        //add(new GameContainer(), "game");

        GameContainer gameContainer = new GameContainer();
        add(gameContainer, "game");
    }

    /**
     * Returns the component with the specified name (matching {@code getName()}).
     *
     * @param name the name of the component to find
     * @return the matching component, or {@code null} if not found
     */
    private Component getComponent(String name) {
        for (Component comp : getComponents()) {
            if (name.equals(comp.getName())) {
                return comp;
            }
        }
        return null;
    }
}
