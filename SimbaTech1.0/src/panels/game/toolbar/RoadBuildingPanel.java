package panels.game.toolbar;

import core.Resources;
import panels.game.toolbar.buttons.road.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class RoadBuildingPanel extends JPanel {

    private SaveButton saveButton;
    private DeleteButton deleteButton;
    private JLabel text;

    public RoadBuildingPanel() {
        saveButton = new SaveButton();
        deleteButton = new DeleteButton();
        text = new JLabel("Építés mód", SwingConstants.CENTER);

        setLayout(null);
        setBackground(Color.BLACK);

        // Szöveg címke létrehozása
        text.setFont(Resources.Instance.menu_font.deriveFont(35f));
        text.setForeground(Color.WHITE);
        text.setBounds(0, 10, 200, 50);

        add(text);
        add(saveButton);
        add(deleteButton);

        // Figyeljük a panel méretének változását
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeComponents();
            }
        });
    }

    // A komponensek átméretezése és pozicionálása a panel méretének változásakor
    private void resizeComponents() {
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Szöveg középre igazítása
        int textWidth = 200;
        int textHeight = 50;
        text.setBounds(0, 0, textWidth, textHeight);

        // Gombok pozicionálása
        int buttonWidth = 100;
        int buttonHeight = 60;
        int space = 10; // Távolság a gombok között

        saveButton.setBounds((panelWidth - 2 * buttonWidth - space) / 2, panelHeight - buttonHeight - 40, buttonWidth, buttonHeight);
        deleteButton.setBounds((panelWidth + buttonWidth + space) / 2, panelHeight - buttonHeight - 40, buttonWidth, buttonHeight);
    }
}
