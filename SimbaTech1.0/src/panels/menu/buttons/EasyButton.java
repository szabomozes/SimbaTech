package panels.menu.buttons;


import core.Resources;
import logic.Difficulty;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EasyButton extends StartButton {
    public EasyButton() {
        super("Könnyű");
        message = Difficulty.EASY;

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
    }
}