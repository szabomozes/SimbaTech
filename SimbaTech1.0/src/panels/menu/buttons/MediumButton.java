package panels.menu.buttons;

import core.Resources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MediumButton extends StartButton {
    public MediumButton() {
        super("Közepes");

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start("Közepes");
            }
        });
    }
}