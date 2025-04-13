package panels.feedback;

import core.Resources;
import panels.CardPanel;
import panels.game.EventPanel;
import panels.game.toolbar.ToolBarCardLayout;
import safari.Safari;

import javax.swing.*;

/**
 * A panel, amely egy üzenetet jelenít meg a felhasználónak és egy gombot biztosít a továbblépéshez.
 * Az üzenet és a gomb lehetővé teszi a felhasználó számára, hogy elhagyja a visszajelzést és visszatérjen a játékhoz.
 */
public class MessageFeedBackPanel extends BasicFeedBackPanel {

    /**
     * Konstruktor, amely létrehozza az üzenetet tartalmazó panelt és egy gombot,
     * amely visszatér a megadott eszköztár kártyához, valamint eltávolítja a visszajelzést.
     *
     * @param text               Az üzenet, amelyet meg kell jeleníteni a felhasználónak.
     * @param toolBarCardLayout Az eszköztár kártya neve, amelyre a felhasználó visszatérhet a gomb megnyomásával.
     */
    public MessageFeedBackPanel(String text, String toolBarCardLayout) {
        super(text, Resources.Instance.badFeddBack);

        JButton button = new JButton("Rendben");
        button.setFocusPainted(false);
        button.setFont(Resources.Instance.menu_font.deriveFont(20f));
        button.addActionListener(e -> {
            ToolBarCardLayout.Instance.showCard(toolBarCardLayout);
            ((EventPanel) getParent()).setFeedback(null);
        });

        add(button, gbc);
    }
}
