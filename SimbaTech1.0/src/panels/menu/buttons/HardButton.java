package panels.menu.buttons;

import core.Resources;
import logic.Difficulty;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HardButton extends StartButton {
    public HardButton() {
        super("Nehéz");
        message = Difficulty.MEDIUM;

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
    }
}