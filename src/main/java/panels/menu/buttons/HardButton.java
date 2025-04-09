package panels.menu.buttons;

import safari.DifficultyEnum;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HardButton extends StartButton {
    public HardButton() {
        super("Nehéz");
        message = DifficultyEnum.HARD;

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
    }
}