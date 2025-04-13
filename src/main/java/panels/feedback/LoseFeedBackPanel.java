package panels.feedback;

import core.Resources;
import panels.CardPanel;
import safari.Safari;
import timer.WinOrLoseTimer;

import javax.swing.*;

/**
 * A panel, amely akkor jelenik meg, amikor a játékos elveszíti a játékot.
 * A panel tartalmazza a "Vesztettél!" üzenetet és egy gombot, amely lehetővé teszi a játékos számára, hogy kilépjen a játékból.
 */
public class LoseFeedBackPanel extends BasicFeedBackPanel {

    /**
     * Konstruktor, amely létrehozza a vesztéshez tartozó visszajelző panelt.
     * A panel tartalmazza a "Vesztettél!" szöveget és egy kilépés gombot.
     */
    public LoseFeedBackPanel() {
        super("Vesztettél!", Resources.Instance.loseFeddBack);
        add(getButton(), gbc);
    }

    /**
     * Létrehozza a kilépés gombot, amely leállítja az időzítőt és bezárja a játékot,
     * majd visszatér a főmenübe.
     *
     * @return A kilépés gomb.
     */
    private JButton getButton() {
        WinOrLoseTimer.getInstance().stopTimer();
        Safari.Instance.shutDown();
        JButton button = new JButton("Kilépés");
        button.setFocusPainted(false);
        button.setFont(Resources.Instance.menu_font.deriveFont(20f));
        button.addActionListener(e -> {
            Safari.Instance.shutDown();
            CardPanel.Instance.showCard("menu");
        });
        return button;
    }
}
