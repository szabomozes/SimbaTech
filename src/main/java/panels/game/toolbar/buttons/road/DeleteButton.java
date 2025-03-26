package panels.game.toolbar.buttons.road;

import core.Resources;
import panels.game.toolbar.ToolBarCardLayout;
import panels.game.toolbar.buttons.shop.BasicLeftToolBarButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButton extends BasicLeftToolBarButton {
    public DeleteButton() {
        super(Resources.Instance.deleteButton, 600, 45);
        message = "delete";

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("back to toolbar");
                CardLayout cardLayout = (CardLayout) ToolBarCardLayout.Instance.getLayout();
                cardLayout.show(ToolBarCardLayout.Instance, "toolbar");
            }
        });
    }
}
