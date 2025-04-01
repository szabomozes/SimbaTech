package panels.game;

import panels.feedback.LoseFeedBackPanel;
import panels.feedback.MessageFeedBackPanel;
import panels.feedback.WinFeedBackPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FeedBackTriggerButton extends JButton {
    public FeedBackTriggerButton() {
        setText("WinFeedback");
        setSize(new Dimension(100, 50));
        setBounds(10, 80, getWidth(), getHeight());

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((EventPanel) getParent()).setFeedback(new MessageFeedBackPanel("Idk, it's a message!"));
            }
        });

    }
}
