package panels.menu.buttons;

import safari.DifficultyEnum;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MediumButton extends StartButton {
    public MediumButton() {
        super("Közepes");
        message = DifficultyEnum.MEDIUM;

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
    }
}