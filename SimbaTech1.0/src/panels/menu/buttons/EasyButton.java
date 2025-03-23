package panels.menu.buttons;


import logic.DifficultyEnum;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EasyButton extends StartButton {
    public EasyButton() {
        super("Könnyű");
        message = DifficultyEnum.EASY;

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
    }
}