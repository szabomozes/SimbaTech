package panels.menu.text;


import core.Resources;

import javax.swing.*;
import java.awt.*;

public class TitleLabel extends JLabel{
    public TitleLabel() {
        super("Simbatech");
        setFont(Resources.Instance.menu_font.deriveFont(Font.BOLD, 50f));
        setForeground(Color.BLACK);
    }
}
