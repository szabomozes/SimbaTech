package entity.notmobile.plants;

import core.Resources;
import entity.NotMobileEntity;

import javax.swing.*;

public class PalmTree extends Plant {
    public PalmTree(double x, double y) {
        super(x, y, new ImageIcon(Resources.Instance.palmTree));
    }
}
