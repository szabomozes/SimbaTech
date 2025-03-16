package panels.menu.icon;

import core.Resource;

import javax.swing.*;

public class LogoLabel extends JLabel {
    public LogoLabel() {
        super(new ImageIcon(Resource.Instance.menu_logo));
    }
}
