package panels.menu.text;


import core.Resources;

import javax.swing.*;
import java.awt.*;

public class SubtitleLabel extends JLabel{
    public SubtitleLabel() {
        super("A vadon a te játszótered - építsd, védd meg, urald");
        setFont(Resources.Instance.menu_font.deriveFont(30f));
        setForeground(Color.BLACK);
    }
}
