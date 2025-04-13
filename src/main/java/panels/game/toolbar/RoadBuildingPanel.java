package panels.game.toolbar;

import core.Resources;
import panels.game.toolbar.buttons.road.DeleteButton;
import panels.game.toolbar.buttons.road.SaveButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * The RoadBuildingPanel class represents a panel in the game for building roads.
 * It includes buttons for saving and deleting roads and displays a label with the text "Építés mód" (Build Mode).
 */
public class RoadBuildingPanel extends JPanel {

    private SaveButton saveButton;
    private DeleteButton deleteButton;
    private JLabel text;

    /**
     * Constructor for the RoadBuildingPanel class. It initializes the panel with the save and delete buttons,
     * and a label displaying "Építés mód". It also sets the layout and background, and listens for component resizing events.
     */
    public RoadBuildingPanel() {
        saveButton = new SaveButton();
        deleteButton = new DeleteButton();
        text = new OutlineLabel("Építés mód");

        setLayout(null);
        setBackground(new Color(40, 40, 40));
        setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));

        // Set the font, color, and position for the label
        text.setFont(Resources.Instance.menu_font.deriveFont(35f));
        text.setForeground(Color.WHITE);
        text.setBounds(0, 10, 200, 50);

        add(text);
        add(saveButton);
        add(deleteButton);

        // Listen for panel resize events to adjust component sizes and positions
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeComponents();
            }
        });
    }

    /**
     * Resizes and repositions the components of the panel whenever the panel's size changes.
     * It adjusts the label and button positions based on the current panel size.
     */
    private void resizeComponents() {
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Center the label
        int textWidth = 200;
        int textHeight = 50;
        text.setBounds(0, 0, textWidth, textHeight);

        // Position the buttons
        int buttonWidth = 100;
        int buttonHeight = 60;
        int space = 10; // Space between buttons

        saveButton.setBounds((panelWidth - 2 * buttonWidth - space) / 2, panelHeight - buttonHeight - 40, buttonWidth, buttonHeight);
        deleteButton.setBounds((panelWidth + buttonWidth + space) / 2, panelHeight - buttonHeight - 40, buttonWidth, buttonHeight);
    }
}
