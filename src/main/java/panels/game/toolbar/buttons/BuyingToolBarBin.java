package panels.game.toolbar.buttons;

import core.Resources;
import safari.Safari;
import panels.game.toolbar.ToolBarCardLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyingToolBarBin extends JButton {
    public BuyingToolBarBin() {
        ImageIcon image = new ImageIcon(Resources.Instance.binButton);
        setIcon(image);

        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ToolBarCardLayout.Instance.showCard("toolbar");
                Safari.Instance.shopping = null;
            }
        });
    }
}
