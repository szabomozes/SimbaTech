package panels.feedback;

import core.Resources;
import safari.Safari;
import panels.CardPanel;
import timer.WinOrLoseTimer;

import javax.swing.*;

/**
 * A panel, amely akkor jelenik meg, amikor a játékos megnyeri a játékot.
 * A panel tartalmazza a "Gratulálok! Csodálatos munkát végeztél!" üzenetet és egy gombot, amely lehetővé teszi a játékos számára, hogy kilépjen a játékból.
 */
public class WinFeedBackPanel extends BasicFeedBackPanel {

    /**
     * Konstruktor, amely létrehozza a nyeréshez tartozó visszajelző panelt.
     * A panel tartalmazza a "Gratulálok! Csodálatos munkát végeztél!" szöveget és egy kilépés gombot.
     */
    public WinFeedBackPanel() {
        super("Gratulálok! Csodálatos munkát végeztél!", Resources.Instance.winFeddBack);
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
