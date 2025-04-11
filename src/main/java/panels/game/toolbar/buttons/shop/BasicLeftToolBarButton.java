package panels.game.toolbar.buttons.shop;

import panels.game.toolbar.SellingToolBar;
import panels.game.toolbar.ToolBarCardLayout;
import safari.Safari;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public abstract class BasicLeftToolBarButton extends JButton {

    protected String message;

    public BasicLeftToolBarButton(BufferedImage image, int x, int y) {
        ImageIcon icon = new ImageIcon(image);
        setIcon(icon);
        int width = icon.getIconWidth();
        int height = icon.getIconHeight();
        setBounds(x, y, width, height);

        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (message.equals("sell")) {
                    new SellingToolBar();
                    ToolBarCardLayout.Instance.showCard("selling");
                    Safari.Instance.setSellingMode(true);
                } else {
                    Safari.Instance.buySomething(message);
                }
            }
        });

    }
}
