package entity.notmobile.plants;

import core.Resources;
import entity.NotMobileEntity;

import javax.swing.*;

public class Pancium extends Plant {
    public Pancium(double x, double y) {
        super(x, y, new ImageIcon(Resources.Instance.pancium));
    }
}
