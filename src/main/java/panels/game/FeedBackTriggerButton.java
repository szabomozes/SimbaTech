package panels.game;

import panels.feedback.MessageFeedBackPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A button that, when clicked, displays a message feedback panel on the screen.
 * Intended for testing or triggering UI feedback effects in the EventPanel.
 */
public class FeedBackTriggerButton extends JButton {

    /**
     * Constructs a new FeedBackTriggerButton with predefined size, text,
     * and behavior to show a message feedback panel.
     */
    public FeedBackTriggerButton() {
        // Set the button's display text
        setText("WinFeedback");

        // Set size and position
        setSize(new Dimension(100, 50));
        setBounds(10, 80, getWidth(), getHeight());

        // Add a click event listener to trigger feedback display
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display a message feedback panel inside the parent EventPanel
                ((EventPanel) getParent()).setFeedback(
                        new MessageFeedBackPanel("Idk, it's a message!", "toolbar")
                );
            }
        });
    }
}
