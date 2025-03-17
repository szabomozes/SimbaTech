package panels.menu.icon;

import core.Resources;

import javax.swing.*;

public class LogoLabel extends JLabel {
    public LogoLabel() {
        super(new ImageIcon(Resources.Instance.menu_logo));
    }
}
