package panels.menu.buttons;

import core.Resource;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MediumButton extends StartButton {
    public MediumButton() {
        super("Közepes");
        this.setFont(Resource.Instance.menu_font.deriveFont(35f));
        this.setFocusPainted(false);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start("Közepes");
            }
        });
    }
}