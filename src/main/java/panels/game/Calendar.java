package panels.game;

import core.Resources;
import panels.CardPanel;

import javax.swing.*;
import java.awt.*;

/**
 * A Calendar osztály egy naptár panelt reprezentál, amely megjeleníti az aktuális dátumot egy ikon segítségével.
 * Az ikon mellett az aktuális dátum szöveges formában is megjelenik.
 * A panel pozíciója és tartalma dinamikusan frissíthető.
 */
public class Calendar extends JLabel {

    /**
     * Az aktuális dátum, amely kezdetben 1-re van állítva.
     */
    private int date = 1;

    /**
     * A naptár egyetlen példányát reprezentáló statikus változó.
     */
    public static Calendar Instance = new Calendar();

    /**
     * Konstruktor, amely beállítja a naptár ikonját, méretét, pozícióját és megjelenítési beállításait.
     * Az aktuális dátumot is frissíti az ikon alatt.
     */
    private Calendar() {
        ImageIcon icon = new ImageIcon(Resources.Instance.calender);
        setIcon(icon);

        int width = icon.getIconWidth();
        int height = icon.getIconHeight();
        setBounds(CardPanel.Instance.getWidth() - width - 10, 10, width, height);

        setOpaque(false);

        setHorizontalTextPosition(JButton.CENTER);
        setVerticalTextPosition(JButton.CENTER);
        setFont(Resources.Instance.menu_font.deriveFont(35f));
        setForeground(Color.BLACK);

        updateText();
    }

    /**
     * Frissíti a dátum szöveget és újrarajzolja a naptár panelt.
     */
    private void updateText() {
        setText(String.valueOf(date));
        repaint();
    }

    /**
     * Frissíti a naptár pozícióját a képernyőn.
     * Az új pozíció a CardPanel szülő panel szélességét figyelembe véve van kiszámítva.
     */
    public void updatePosition() {
        setBounds(CardPanel.Instance.getWidth() - getWidth() - 10, 10, getWidth(), getHeight());
    }

    /**
     * Beállítja az új dátumot és frissíti a naptár szöveges megjelenítését.
     *
     * @param date Az új dátum érték.
     */
    public void setDate(int date) {
        this.date = date;
        updateText();
    }

}
