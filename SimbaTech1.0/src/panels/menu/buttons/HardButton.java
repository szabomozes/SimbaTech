package panels.menu.buttons;

import core.Resources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HardButton extends StartButton {
    public HardButton() {
        super("Nehéz");
        this.setFont(Resources.Instance.menu_font.deriveFont(35f));
        this.setFocusPainted(false);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start("Nehéz");
            }
        });
    }
}