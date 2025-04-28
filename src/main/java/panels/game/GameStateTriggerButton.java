package panels.game;

import core.Resources;
import panels.CardPanel;
import panels.feedback.GameStatePanel;
import panels.game.toolbar.ToolBarCardLayout;
import safari.Safari;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A custom button used to trigger the display of the {@link GameStatePanel}.
 *
 * <p>This button is disabled when a ranger is selected or the game has ended.
 * When clicked, it attempts to find the parent {@link EventPanel} and displays the game state panel in it.</p>
 */
public class GameStateTriggerButton extends JButton {

    public static GameStateTriggerButton Instance = new GameStateTriggerButton();
    private boolean clicked = true;

    public GameStateTriggerButton() {
        ImageIcon icon = new ImageIcon(Resources.Instance.info);
        setIcon(icon);

        int width = icon.getIconWidth();
        int height = icon.getIconHeight();
        setBounds(CardPanel.Instance.getWidth() - width - 70, 10, width, height);

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        setRolloverEnabled(false);
        setFocusPainted(false);

        setHorizontalTextPosition(JButton.CENTER);
        setVerticalTextPosition(JButton.CENTER);
        setFont(Resources.Instance.menu_font.deriveFont(35f));
        setForeground(Color.BLACK);

        setEnabled(clicked);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Safari.Instance.isSelectedRanger() || !Safari.Instance.getWinOrLose().isEmpty()) {
                    return;
                }
                try {
                    setClicked(false);
                    Container parent = getParent();
                    // Traverse up the component hierarchy to find the EventPanel
                    while (parent != null && !(parent instanceof EventPanel)) {
                        parent = parent.getParent();
                    }

                    if (parent != null) {
                        ((EventPanel) parent).setStatePanel(new GameStatePanel(ToolBarCardLayout.Instance.getCurrentCardName()));
                        parent.repaint();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    /**
     * Sets whether the button can be clicked.
     *
     * @param clicked true to enable the button, false to disable it.
     */
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
        setEnabled(clicked && !Safari.Instance.isSelectedRanger());
    }

    /**
     * Updates the button's position on the screen, aligning it to the top right of the {@link CardPanel}.
     */
    public void updatePosition() {
        setBounds(CardPanel.Instance.getWidth() - getWidth() - 70, 10, getWidth(), getHeight());
    }

    /**
     * Updates the enabled state of the button based on its current clickability and ranger selection state.
     */
    public void updateButtonState() {
        setEnabled(clicked && !Safari.Instance.isSelectedRanger());
    }
}
